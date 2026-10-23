package com.uagr.kmp.course.presentation.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.usecase.dashboard.GetDashboardUseCase
import com.uagr.kmp.course.domain.usecase.user.GetUserCase
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

class HomeViewModel(
    private var getUserTokenCase: GetUserTokenCase,
    private var dashboardUseCase: GetDashboardUseCase,
    private var getUserCase: GetUserCase,
): ViewModel() {
    private var _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()
    private var _homeUiEvent = MutableStateFlow<HomeUiEvent>(HomeUiEvent.Idle)
    val homeUiEvent: StateFlow<HomeUiEvent> = _homeUiEvent.asStateFlow()

    init {
        getDashboard()
        getName()
    }
    private fun getName() = viewModelScope.launch {
        val user = getUserCase.invoke()
        _homeUiState.update { state -> state.copy(name = user?.name ?: "") }
    }
    private fun getDashboard() = viewModelScope.launch {
        dashboardUseCase.callService(
            url = NetworkUrl.DASHBOARD_ENDPOINT,
            access_token = getUserTokenCase.invoke() ?: ""
        ).onStart {
            _homeUiState.update { state -> state.copy(isLoading = StatusLoading.SHOW_LOADING) }
        }.catch {
            _homeUiState.update { state ->
                state.copy(
                    isLoading = StatusLoading.DISMISS_LOADING,
                    errorDialog = setErrorDialog()
                )
            }
        }.collect { result ->
            when(result){
                is NetworkResult.Error -> {
                    _homeUiState.update { state ->
                        state.copy(
                            isLoading = StatusLoading.DISMISS_LOADING,
                            errorDialog = setErrorDialog()
                        )
                    }
                }
                is NetworkResult.Success -> {
                    result.response?.let { data ->
                        _homeUiState.update { state -> state.copy(totalBalance = data.total_balance, monthlyIncome = data.monthly_income, monthlyExpenses = data.monthly_expenses, monthlySavings = data.monthly_expenses, savingsRate = data.savings_rate, recentTransactions = data.recent_transactions) }
                    } ?: run {
                        _homeUiState.update { state ->
                            state.copy(
                                isLoading = StatusLoading.DISMISS_LOADING,
                                errorDialog = setErrorDialog(),
                            )
                        }
                    }
                }
            }
        }
    }
    private suspend fun setErrorDialog(message: String? = null): ErrorDialogModel =
        ErrorDialogModel(
            title = getString(resource = Res.string.error),
            message = message ?: getString(resource = Res.string.please_try_again_later),
            primaryButtonText = getString(resource = Res.string.accept),
        )

    fun dismissErrorDialog() = viewModelScope.launch {
        _homeUiState.update { state -> state.copy(errorDialog = null) }
    }
}