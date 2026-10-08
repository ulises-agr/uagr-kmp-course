/*
 * LoginViewModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.usecase.login.LoginUseCase
import com.uagr.kmp.course.domain.usecase.login.loginValidationResult
import com.uagr.kmp.course.domain.usecase.login.ValidateLoginUseCase
import com.uagr.kmp.course.domain.usecase.user.SaveUserTokenUseCase
import com.uagr.kmp.course.utils.constant.NetworkUrl
import com.uagr.kmp.course.utils.network.NetworkErrorHandler
import com.uagr.kmp.course.utils.network.NetworkErrorType
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.operators.StatusLoading
import course.shared.generated.resources.Res
import course.shared.generated.resources.accept
import course.shared.generated.resources.email_empty
import course.shared.generated.resources.email_invalid
import course.shared.generated.resources.error
import course.shared.generated.resources.network_error
import course.shared.generated.resources.password_empty
import course.shared.generated.resources.please_try_again_later
import io.github.aakira.napier.Napier
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
class LoginViewModel(
    private val validateLoginFormUseCase: ValidateLoginUseCase,
    private val loginUseCase: LoginUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase,
    private val networkErrorHandler: NetworkErrorHandler,
) : ViewModel() {

    private var _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    private var _loginUiEvent = MutableStateFlow<LoginUiEvent>(LoginUiEvent.Idle)
    val loginUiEvent: StateFlow<LoginUiEvent> = _loginUiEvent.asStateFlow()

    fun updateEmail(email: String) = viewModelScope.launch {
        _loginUiState.update { state -> state.copy(email = email) }
    }

    fun updatePassword(password: String) = viewModelScope.launch {
        _loginUiState.update { state -> state.copy(password = password) }
    }

    fun togglePasswordVisibility() = viewModelScope.launch {
        _loginUiState.update { state ->
            state.copy(isPasswordVisible = !state.isPasswordVisible)
        }
    }

    fun validateLoginForm(
        email: String,
        password: String,
    ) = viewModelScope.launch {
        when (validateLoginFormUseCase(
            email = email,
            password = password,
        )) {
            is loginValidationResult.EmailEmpty -> {
                _loginUiState.update { state ->
                    state.copy(errorDialog = setErrorDialog(message = getString(Res.string.email_empty)))
                }
            }
            is loginValidationResult.EmailInvalid -> {
                _loginUiState.update { state ->
                    state.copy(errorDialog = setErrorDialog(message = getString(Res.string.email_invalid)))
                }
            }
            is loginValidationResult.PasswordEmpty -> {
                _loginUiState.update { state ->
                    state.copy(errorDialog = setErrorDialog(message = getString(Res.string.password_empty)))
                }
            }
            is loginValidationResult.Success -> {
                login(
                    email = email,
                    password = password,
                )
            }
        }
    }

    private fun login(
        email: String,
        password: String,
    ) = viewModelScope.launch {
        loginUseCase.login(
            url = NetworkUrl.LOGIN_ENDPOINT,
            email = email,
            password = password,
        ).onStart {
            Napier.d("onStart")
            _loginUiState.update { state ->
                state.copy(
                    isLoading = StatusLoading.SHOW_LOADING,
                    errorDialog = null,
                )
            }
        }.catch {
            _loginUiState.update { state ->
                Napier.d("catch")
                state.copy(
                    isLoading = StatusLoading.DISMISS_LOADING,
                    errorDialog = setErrorDialog(message = getString(Res.string.network_error)),
                )
            }
        }.collect { result ->
            when (result) {
                is NetworkResult.Success -> {
                    Napier.d("Success")
                    val loginData = result.response
                    if (loginData.accessToken.isNotBlank()) {
                        saveUserToken(token = loginData.accessToken)
                        _loginUiState.update { state ->
                            state.copy(isLoading = StatusLoading.DISMISS_LOADING)
                        }
                        _loginUiEvent.emit(LoginUiEvent.LoginSuccess)
                    } else {
                        val errorDialog = networkErrorHandler.buildErrorDialogModel(
                            errorType = NetworkErrorType.UNKNOWN
                        )
                        _loginUiState.update { state ->
                            state.copy(
                                isLoading = StatusLoading.DISMISS_LOADING,
                                errorDialog = errorDialog,
                            )
                        }
                    }
                }
                is NetworkResult.Error -> {
                    Napier.d("Error")
                    var errorDialog = networkErrorHandler.buildErrorDialogModel(
                        errorType = result.errorType,
                        httpCode = result.code,
                        serverMessage = result.message,
                    )
                    _loginUiState.update {
                        state -> state.copy(isLoading = StatusLoading.DISMISS_LOADING, errorDialog = errorDialog) }
                }
            }
        }
    }

    private fun saveUserToken(token: String) = viewModelScope.launch {
        saveUserTokenUseCase(token = token)
            .catch {
                val errorDialog = networkErrorHandler.buildErrorDialogModel(
                    errorType = NetworkErrorType.UNKNOWN
                )
                _loginUiState.update { state ->
                    state.copy(
                        isLoading = StatusLoading.DISMISS_LOADING,
                        errorDialog = errorDialog,
                    )
                }
            }.collect {
                _loginUiState.update { state ->
                    state.copy(isLoading = StatusLoading.DISMISS_LOADING)
                }
                _loginUiEvent.emit(LoginUiEvent.LoginSuccess)
            }
    }
    private suspend fun setErrorDialog(message: String? = null): ErrorDialogModel =
        ErrorDialogModel(
            title = getString(resource = Res.string.error),
            message = message ?: getString(resource = Res.string.please_try_again_later),
            primaryButtonText = getString(resource = Res.string.accept),
        )

    fun dismissErrorDialog() = viewModelScope.launch {
        _loginUiState.update { state -> state.copy(errorDialog = null) }
    }

    fun resetUiEvent() = viewModelScope.launch {
        _loginUiEvent.value = LoginUiEvent.Idle
    }
}
