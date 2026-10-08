/*
 * RegisterViewModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.register.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.usecase.register.RegisterUserUseCase
import com.uagr.kmp.course.domain.usecase.user.SaveUserTokenUseCase
import com.uagr.kmp.course.utils.network.NetworkErrorHandler
import com.uagr.kmp.course.utils.network.NetworkErrorType
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.operators.StatusLoading
import course.shared.generated.resources.Res
import course.shared.generated.resources.accept
import course.shared.generated.resources.error
import course.shared.generated.resources.network_error
import course.shared.generated.resources.please_try_again_later
import course.shared.generated.resources.server_error
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString

class RegisterViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase,
    private val networkErrorHandler: NetworkErrorHandler,
) : ViewModel() {

    private var _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState: StateFlow<RegisterUiState> = _registerUiState.asStateFlow()

    private var _registerUiEvent = MutableStateFlow<RegisterUiEvent>(RegisterUiEvent.Idle)
    val registerUiEvent: StateFlow<RegisterUiEvent> = _registerUiEvent.asStateFlow()

    fun updateName(name: String) = viewModelScope.launch {
        _registerUiState.update { state -> state.copy(name = name) }
    }

    fun updateEmail(email: String) = viewModelScope.launch {
        _registerUiState.update { state -> state.copy(email = email) }
    }

    fun updatePassword(password: String) = viewModelScope.launch {
        _registerUiState.update { state -> state.copy(password = password) }
    }

    fun updateConfirmPassword(confirmPassword: String) = viewModelScope.launch {
        _registerUiState.update { state -> state.copy(confirmPassword = confirmPassword) }
    }

    fun togglePasswordVisibility() = viewModelScope.launch {
        _registerUiState.update { state -> state.copy(isPasswordVisible = !state.isPasswordVisible)
        }
    }

    fun toggleConfirmPasswordVisibility() = viewModelScope.launch {
        _registerUiState.update { state -> state.copy(isConfirmPasswordVisible = !state.isConfirmPasswordVisible)
        }
    }

    fun validateRegisterForm(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
    ) = viewModelScope.launch {
        registerUserUseCase(
            name = name,
            email = email,
            password = password,
            confirmPassword = confirmPassword,
        ).onStart {
            _registerUiState.update { state ->
                state.copy(
                    isLoading = StatusLoading.SHOW_LOADING,
                    errorDialog = null,
                )
            }
        }.catch {
            _registerUiState.update { state ->
                state.copy(
                    isLoading = StatusLoading.DISMISS_LOADING,
                    errorDialog = setErrorDialog(message = getString(Res.string.network_error)),
                )
            }
        }.collect { result ->
            when (result) {
                is NetworkResult.Success -> {
                    val tokens = result.response.tokens
                    if (tokens != null && tokens.access_token.isNotBlank()) {
                        saveUserToken(token = tokens.access_token)
                    } else {
                        _registerUiState.update { state ->
                            state.copy(isLoading = StatusLoading.DISMISS_LOADING)
                        }
                        _registerUiEvent.emit(RegisterUiEvent.RegisterSuccess)
                    }
                }
                is NetworkResult.Error -> {
                    val errorDialog = networkErrorHandler.buildErrorDialogModel(
                        errorType = result.errorType,
                        httpCode = result.code,
                        serverMessage = result.message,
                    )
                    _registerUiState.update {
                        state -> state.copy(isLoading = StatusLoading.DISMISS_LOADING, errorDialog = errorDialog)
                    }
                }
            }
        }
    }

    private fun saveUserToken(token: String) = viewModelScope.launch {
        saveUserTokenUseCase(token = token)
            .catch {
                _registerUiState.update { state ->
                    state.copy(isLoading = StatusLoading.DISMISS_LOADING)
                }
                _registerUiEvent.emit(RegisterUiEvent.RegisterSuccess)
            }.collect {
                _registerUiState.update { state ->
                    state.copy(isLoading = StatusLoading.DISMISS_LOADING)
                }
                _registerUiEvent.emit(RegisterUiEvent.RegisterSuccess)
            }
    }

    private suspend fun setErrorDialog(message: String? = null): ErrorDialogModel =
        ErrorDialogModel(
            title = getString(resource = Res.string.error),
            message = message ?: getString(resource = Res.string.please_try_again_later),
            primaryButtonText = getString(resource = Res.string.accept),
        )

    fun dismissErrorDialog() = viewModelScope.launch {
        _registerUiState.update { state -> state.copy(errorDialog = null) }
    }

    fun resetUiEvent() = viewModelScope.launch {
        _registerUiEvent.value = RegisterUiEvent.Idle
    }
}