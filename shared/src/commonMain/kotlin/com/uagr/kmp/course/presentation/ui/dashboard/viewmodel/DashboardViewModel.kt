/*
 * DashboardViewModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.usecase.user.GetFirstUserUseCase
import com.uagr.kmp.course.util.enums.StatusLoading
import course.shared.generated.resources.Res
import course.shared.generated.resources.please_try_again_later
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getFirstUserUseCase: GetFirstUserUseCase,
) : ViewModel() {

    private var _dashboardUiState = MutableStateFlow(DashboardUiState())
    val dashboardUiState: StateFlow<DashboardUiState> = _dashboardUiState.asStateFlow()

    private var _dashboardUiEvent = MutableStateFlow<DashboardUiEvent>(DashboardUiEvent.Idle)
    val dashboardUiEvent: StateFlow<DashboardUiEvent> = _dashboardUiEvent.asStateFlow()

    init {
        getFirstUser()
    }

    private fun getFirstUser() = viewModelScope.launch {
        getFirstUserUseCase.getFirstUser()
            .onStart {
                _dashboardUiState.update { state -> state.copy(isLoading = StatusLoading.SHOW_LOADING) }
            }.catch {
                _dashboardUiEvent.emit(DashboardUiEvent.ShowErrorDialog(message = Res.string.please_try_again_later))
                _dashboardUiState.update { state -> state.copy(isLoading = StatusLoading.DISMISS_LOADING) }
            }.collect { result ->
                result?.let { result ->
                    _dashboardUiState.update { state -> state.copy(isLoading = StatusLoading.DISMISS_LOADING) }
                    _dashboardUiState.update { state -> state.copy(email = result.email) }
                } ?: run {
                    _dashboardUiEvent.emit(DashboardUiEvent.ShowErrorDialog(message = Res.string.please_try_again_later))
                    _dashboardUiState.update { state -> state.copy(isLoading = StatusLoading.DISMISS_LOADING) }
                }
            }
    }

    fun resetUiEvent() = viewModelScope.launch {
        _dashboardUiEvent.value = DashboardUiEvent.Idle
    }
}
