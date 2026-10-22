package com.uagr.kmp.course.presentation.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.usecase.home.GetDashboardUseCase
import com.uagr.kmp.course.utils.constant.NetworkUrl
import com.uagr.kmp.course.utils.network.NetworkErrorHandler
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.operators.StatusLoading
import course.shared.generated.resources.Res
import course.shared.generated.resources.accept
import course.shared.generated.resources.error
import course.shared.generated.resources.network_error
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
class HomeViewModel(
    private val getDashboardUseCase: GetDashboardUseCase,
    private val networkErrorHandler: NetworkErrorHandler,
) : ViewModel() {

    private var _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    private var _homeUiEvent = MutableStateFlow<HomeUiEvent>(HomeUiEvent.Idle)
    val homeUiEvent: StateFlow<HomeUiEvent> = _homeUiEvent.asStateFlow()

    init {
        getDashboard()
    }

    fun getDashboard() = viewModelScope.launch {
        getDashboardUseCase(url = NetworkUrl.DASHBOARD_ENDPOINT)
            .onStart {
                _homeUiState.update { state ->
                    state.copy(
                        isLoading = StatusLoading.SHOW_LOADING,
                        errorDialog = null,
                    )
                }
            }
            .catch {
                _homeUiState.update { state ->
                    state.copy(
                        isLoading = StatusLoading.DISMISS_LOADING,
                        errorDialog = setErrorDialog(message = getString(Res.string.network_error)),
                    )
                }
            }
            .collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _homeUiState.update { state ->
                            state.copy(
                                isLoading = StatusLoading.DISMISS_LOADING,
                                dashboard = result.response,
                            )
                        }
                    }
                    is NetworkResult.Error -> {
                        val errorDialog = networkErrorHandler.buildErrorDialogModel(
                            errorType = result.errorType,
                            httpCode = result.code,
                            serverMessage = result.message,
                        )
                        _homeUiState.update { state ->
                            state.copy(
                                isLoading = StatusLoading.DISMISS_LOADING,
                                errorDialog = errorDialog,
                            )
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

    fun resetUiEvent() = viewModelScope.launch {
        _homeUiEvent.value = HomeUiEvent.Idle
    }
}