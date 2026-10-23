package com.uagr.kmp.course.presentation.ui.buget.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
//import com.uagr.kmp.course.domain.usecase.goals.GetBugetsUseCase
import com.uagr.kmp.course.domain.usecase.user.GetUserTokenCase
import com.uagr.kmp.course.utils.constant.NetworkUrl
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

class BudgetViewModel(
   // val getBugetsUseCase: GetBugetsUseCase,
    val getUserTokenCase: GetUserTokenCase
): ViewModel(){
    private var _budgetUiState = MutableStateFlow<BudgetUiState>(BudgetUiState())
    var budgetUiState: StateFlow<BudgetUiState> = _budgetUiState.asStateFlow()

    private var _budgetUiEvent = MutableStateFlow<BudgetUiEvent>(BudgetUiEvent.Idle)
    var budgetUiEvent : StateFlow<BudgetUiEvent> = _budgetUiEvent.asStateFlow()
    init {
        //getBudgets()
    }
    private fun getBudgets() = viewModelScope.launch{
        val token = getUserTokenCase.invoke()
/*
        getBugetsUseCase.invoke(
            url_Budget = NetworkUrl.LISTBUDGETS_ENDPOINT,
            access_Token = token ?: "",
            url_Category = NetworkUrl.CATEGORIES_ENDPOINT,
        ).onStart {
            _budgetUiState.update { state -> state.copy(isLoading = StatusLoading.SHOW_LOADING) }
        }.catch {
            _budgetUiState.update { state -> state.copy(isLoading = StatusLoading.DISMISS_LOADING, errorDialog = setErrorDialog()) }
        }.collect { result ->
            when(result){
                is NetworkResult.Success -> {
                    _budgetUiState.update { state -> state.copy(isLoading = StatusLoading.DISMISS_LOADING, data = result.response) }
                }
                is NetworkResult.Error -> {
                    _budgetUiState.update { state -> state.copy(isLoading = StatusLoading.DISMISS_LOADING, errorDialog = setErrorDialog()) }
                }
            }

        }*/
    }

    private suspend fun setErrorDialog(message: String? = null): ErrorDialogModel =
        ErrorDialogModel(
            title = getString(resource = Res.string.error),
            message = message ?: getString(resource = Res.string.please_try_again_later),
            primaryButtonText = getString(resource = Res.string.accept),
        )

    fun dismissErrorDialog() = viewModelScope.launch {
        _budgetUiState.update { state -> state.copy(errorDialog = null) }
    }
}

