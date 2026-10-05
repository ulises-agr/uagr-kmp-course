package com.uagr.kmp.course.presentation.ui.master.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import course.shared.generated.resources.Res
import course.shared.generated.resources.accept
import course.shared.generated.resources.error
import course.shared.generated.resources.please_try_again_later
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class MasterViewModel : ViewModel() {

    private var _masterUiState = MutableStateFlow(MasterUiState())
    val masterUiState: StateFlow<MasterUiState> = _masterUiState.asStateFlow()

    private var _masterUiEvent = MutableStateFlow<MasterUiEvent>(MasterUiEvent.Idle)
    val masterUiEvent: StateFlow<MasterUiEvent> = _masterUiEvent.asStateFlow()

    fun updateCurrentTab(tab: MasterTabs) = viewModelScope.launch {
        _masterUiState.update { state -> state.copy(currentTab = tab) }
    }

    private suspend fun setErrorDialog(message: String? = null): ErrorDialogModel =
        ErrorDialogModel(
            title = getString(resource = Res.string.error),
            message = message ?: getString(resource = Res.string.please_try_again_later),
            primaryButtonText = getString(resource = Res.string.accept),
        )

    fun dismissErrorDialog() = viewModelScope.launch {
        _masterUiState.update { state -> state.copy(errorDialog = null) }
    }

    fun resetUiEvent() = viewModelScope.launch {
        _masterUiEvent.value = MasterUiEvent.Idle
    }
}
