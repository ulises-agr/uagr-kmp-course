package com.uagr.kmp.course.presentation.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.usecase.user.GetUserTokenUseCase
import com.uagr.kmp.course.domain.usecase.user.GetUserUseCase
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
class HomeViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val getUserTokenUseCase : GetUserTokenUseCase,
) : ViewModel() {

    private var _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    private var _homeUiEvent = MutableStateFlow<HomeUiEvent>(HomeUiEvent.Idle)
    val homeUiEvent: StateFlow<HomeUiEvent> = _homeUiEvent.asStateFlow()

    init {
        fetchHomeData()
    }

    fun fetchHomeData(query: String = "") = viewModelScope.launch {
        getLocalUser()
        getUserToken()
    }

    fun getLocalUser() = viewModelScope.launch {
        getUserUseCase()
            .catch {
                _homeUiState.update { state -> state.copy(isLoading = StatusLoading.DISMISS_LOADING) }
                //_homeUiEvent.emit(PackagesUiEvent.ShowErrorDialog(message = Res.string.please_try_again_later))
            }.collect { result ->
                _homeUiState.update { state -> state.copy(isLoading = StatusLoading.DISMISS_LOADING, name = result.full_name) }
            }
    }

    fun getUserToken() = viewModelScope.launch {
        getUserTokenUseCase.invoke()
            .catch {
                //Try to Refresh token auth
                //_homeUiState.update { state -> state.copy(isLoading = StatusLoading.DISMISS_LOADING) }
                //_homeUiEvent.emit(PackagesUiEvent.ShowErrorDialog(message = Res.string.please_try_again_later))
            }.collect { result ->
                getDashboardData(token = result)
                getTransactionsData(token = result)
            }
    }

    fun getDashboardData(token: String) = viewModelScope.launch {

    }

    fun getTransactionsData(token: String) = viewModelScope.launch {

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
