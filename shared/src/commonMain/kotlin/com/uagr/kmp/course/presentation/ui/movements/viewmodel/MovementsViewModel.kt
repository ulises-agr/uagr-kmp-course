package com.uagr.kmp.course.presentation.ui.movements.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.usecase.movements.MovementsUseCase
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
class MovementsViewModel(
    private val movementsUseCase: MovementsUseCase,
) : ViewModel() {

    private var _movementsUiState = MutableStateFlow(MovementsUiState())
    val movementsUiState: StateFlow<MovementsUiState> = _movementsUiState.asStateFlow()

    private var _movementsUiEvent = MutableStateFlow<MovementsUiEvent>(MovementsUiEvent.Idle)
    val movementsUiEvent: StateFlow<MovementsUiEvent> = _movementsUiEvent.asStateFlow()

    fun fetchMovementsData(query: String = "") = viewModelScope.launch {
        movementsUseCase.fetchMovementsData(
            url = "",
            query = query,
        ).onStart {
            _movementsUiState.update { state -> state.copy(isLoading = StatusLoading.SHOW_LOADING) }
        }.catch { exception ->
            exception.printStackTrace()
            _movementsUiState.update { state ->
                state.copy(
                    isLoading = StatusLoading.DISMISS_LOADING,
                    errorDialog = setErrorDialog(message = exception.message),
                )
            }
        }.collect { result ->
            when (result) {
                is NetworkResult.Success -> {
                    _movementsUiState.update { state ->
                        state.copy(
                            isLoading = StatusLoading.DISMISS_LOADING,
                            data = result.response.data,
                        )
                    }
                }
                is NetworkResult.Error -> {
                    _movementsUiState.update { state ->
                        state.copy(
                            isLoading = StatusLoading.DISMISS_LOADING,
                            errorDialog = setErrorDialog(message = result.message),
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
        _movementsUiState.update { state -> state.copy(errorDialog = null) }
    }

    fun resetUiEvent() = viewModelScope.launch {
        _movementsUiEvent.value = MovementsUiEvent.Idle
    }
}
