package com.uagr.kmp.course.presentation.ui.budget.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.operators.StatusLoading
import course.shared.generated.resources.Res
import course.shared.generated.resources.accept
import course.shared.generated.resources.error
import course.shared.generated.resources.please_try_again_later
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class BudgetViewModel(
) : ViewModel() {

    private var _budgetUiState = MutableStateFlow(BudgetUiState())
    val budgetUiState: StateFlow<BudgetUiState> = _budgetUiState.asStateFlow()

    private var _budgetUiEvent = MutableStateFlow<BudgetUiEvent>(BudgetUiEvent.Idle)
    val budgetUiEvent: StateFlow<BudgetUiEvent> = _budgetUiEvent.asStateFlow()

    private suspend fun setErrorDialog(message: String? = null): ErrorDialogModel =
        ErrorDialogModel(
            title = getString(resource = Res.string.error),
            message = message ?: getString(resource = Res.string.please_try_again_later),
            primaryButtonText = getString(resource = Res.string.accept),
        )

    fun dismissErrorDialog() = viewModelScope.launch {
        _budgetUiState.update { state -> state.copy(errorDialog = null) }
    }

    fun resetUiEvent() = viewModelScope.launch {
        _budgetUiEvent.value = BudgetUiEvent.Idle
    }
}
