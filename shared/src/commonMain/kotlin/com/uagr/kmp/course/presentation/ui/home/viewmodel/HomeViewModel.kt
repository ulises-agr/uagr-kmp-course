/*
// HomeViewModel.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.usecase.user.GetUserUseCase
import com.uagr.kmp.course.domain.usecase.dashboard.GetDashboardUseCase
import com.uagr.kmp.course.domain.usecase.dashboard.FormatDashboardUseCase
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.operators.StatusLoading
import course.shared.generated.resources.Res
import course.shared.generated.resources.please_try_again_later
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val getDashboardUseCase: GetDashboardUseCase,
    private val formatDashboardUseCase: FormatDashboardUseCase,
) : ViewModel() {
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    private val _homeUiEvent = MutableStateFlow<HomeUiEvent>(HomeUiEvent.Idle)
    val homeUiEvent: StateFlow<HomeUiEvent> = _homeUiEvent.asStateFlow()

    init {
        viewModelScope.launch {
            getUserUseCase()
                .catch { exception ->
                    if (exception is CancellationException) throw exception
                    emit(null)
                }
                .collect { user ->
                    _homeUiState.update { state -> state.copy(userName = user?.name.orEmpty()) }
                }
        }
        loadDashboard()
    }

    fun loadDashboard() {
        if (_homeUiState.value.isLoading == StatusLoading.SHOW_LOADING) return
        _homeUiState.update { state -> state.copy(isLoading = StatusLoading.SHOW_LOADING) }
        viewModelScope.launch {
            getDashboardUseCase()
                .onStart { _homeUiEvent.value = HomeUiEvent.Idle }
                .onEach { result ->
                    if (result is NetworkResult.Success) {
                        val summary = formatDashboardUseCase(result.response)
                        _homeUiState.update { state ->
                            state.copy(
                                totalBalance = summary.totalBalance,
                                incomeAmount = summary.incomeAmount,
                                expensesAmount = summary.expensesAmount,
                                monthlyExpensesAmount = summary.monthlyExpensesAmount,
                                savingsAmount = summary.savingsAmount,
                                expensesChange = summary.budgetUsage,
                                expensesPositiveChange = summary.budgetWithinLimit,
                                savingsChange = summary.savingsRate,
                                savingsPositiveChange = summary.positiveSavingsRate,
                                transactions = summary.transactions,
                                dashboard = result.response,
                            )
                        }
                    }
                }
                .catch { exception ->
                    if (exception is CancellationException) throw exception
                    _homeUiEvent.value = HomeUiEvent.ShowErrorDialog(Res.string.please_try_again_later)
                }
                .onCompletion {
                    _homeUiState.update { state -> state.copy(isLoading = StatusLoading.DISMISS_LOADING) }
                }
                .collect { result ->
                    _homeUiEvent.value = when (result) {
                        is NetworkResult.Success -> HomeUiEvent.Idle
                        is NetworkResult.Error -> HomeUiEvent.ShowErrorStringDialog(result.message)
                    }
                }
        }
    }

    fun clearEvent() {
        _homeUiEvent.value = HomeUiEvent.Idle
    }
}
