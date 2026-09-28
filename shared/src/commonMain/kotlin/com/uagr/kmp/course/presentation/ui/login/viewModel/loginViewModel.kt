/*
 * LoginViewModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.model.user.userTokensModel
import com.uagr.kmp.course.domain.usecase.login.loginUseCase
import com.uagr.kmp.course.domain.usecase.login.loginValidationResult
import com.uagr.kmp.course.domain.usecase.login.validateLoginFormUseCase
import com.uagr.kmp.course.domain.usecase.user.saveUserTokenUseCase
import com.uagr.kmp.course.utils.constant.NetworkUrl
import com.uagr.kmp.course.utils.network.NetworkErrorType
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.operators.StatusLoading
import course.shared.generated.resources.Res
import course.shared.generated.resources.accept
import course.shared.generated.resources.email_empty
import course.shared.generated.resources.email_invalid
import course.shared.generated.resources.error
import course.shared.generated.resources.invalid_credentials
import course.shared.generated.resources.network_error
import course.shared.generated.resources.password_empty
import course.shared.generated.resources.please_try_again_later
import course.shared.generated.resources.server_error
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
class loginViewModel(
    private val validateLoginFormUseCase: validateLoginFormUseCase,
    private val loginUseCase: loginUseCase,
    private val saveUserTokenUseCase: saveUserTokenUseCase,
) : ViewModel() {

    private var _loginUiState = MutableStateFlow(loginUiState())
    val loginUiState: StateFlow<loginUiState> = _loginUiState.asStateFlow()

    private var _loginUiEvent = MutableStateFlow<loginUiEvent>(loginUiEvent.idle)
    val loginUiEvent: StateFlow<loginUiEvent> = _loginUiEvent.asStateFlow()

    fun updateEmail(email: String) = viewModelScope.launch {
        _loginUiState.update { state -> state.copy(email = email) }
    }

    fun updatePassword(password: String) = viewModelScope.launch {
        _loginUiState.update { state -> state.copy(password = password) }
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
                    val tokens = result.response.tokens
                    if (tokens.access_token.isNotBlank()) {
                        //saveUserToken(tokens = tokens)
                    } else {
                        showFriendlyError(errorType = NetworkErrorType.UNKNOWN)
                    }
                }
                is NetworkResult.Error -> {
                    Napier.d("Error")
                    showFriendlyError(
                        errorType = result.errorType,
                        httpCode = result.code,
                        serverMessage = result.message,
                    )
                }
            }
        }
    }

//    private fun saveUserToken(tokens: userTokensModel) = viewModelScope.launch {
//        saveUserTokenUseCase(token = tokens)
//            .catch {
//                showFriendlyError(errorType = NetworkErrorType.UNKNOWN)
//            }.collect {
//                _loginUiState.update { state ->
//                    state.copy(isLoading = StatusLoading.DISMISS_LOADING)
//                }
//                _loginUiEvent.emit(loginUiEvent.loginSuccess)
//            }
//    }

    private suspend fun showFriendlyError(
        errorType: NetworkErrorType,
        httpCode: Int? = null,
        serverMessage: String? = null,
    ) {
        val message = when {
            errorType == NetworkErrorType.NETWORK || errorType == NetworkErrorType.TIMEOUT ->
                getString(Res.string.network_error)
            httpCode == 401 || httpCode == 403 ->
                getString(Res.string.invalid_credentials)
            httpCode == 422 ->
                serverMessage
                    ?.takeIf { msg -> msg.isNotBlank() && !msg.startsWith("Error HTTP") && !msg.startsWith("Error client") }
                    ?: getString(Res.string.invalid_credentials)
            errorType == NetworkErrorType.HTTP && (httpCode != null && httpCode >= 500) ->
                getString(Res.string.server_error)
            else ->
                getString(Res.string.please_try_again_later)
        }
        _loginUiState.update { state ->
            state.copy(
                isLoading = StatusLoading.DISMISS_LOADING,
                errorDialog = setErrorDialog(message = message),
            )
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
        _loginUiEvent.value = loginUiEvent.idle
    }
}
