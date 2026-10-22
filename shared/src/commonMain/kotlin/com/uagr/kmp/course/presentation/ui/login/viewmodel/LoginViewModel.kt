/*
// LoginViewModel.kt
// Created by Karla Judith Cervantes Higareda on 01/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.model.register.TokensModel
import com.uagr.kmp.course.domain.usecase.login.LoginUseCase
import com.uagr.kmp.course.domain.usecase.login.GetDeviceIdUseCase
import com.uagr.kmp.course.domain.usecase.login.LoginValidationError
import com.uagr.kmp.course.domain.usecase.login.ValidateLoginFormUseCase
import com.uagr.kmp.course.domain.usecase.user.SaveUserTokenUseCase
import com.uagr.kmp.course.utils.constant.NetworkUrl
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.operators.StatusLoading
import course.shared.generated.resources.Res
import course.shared.generated.resources.login_invalid_email
import course.shared.generated.resources.login_required_fields
import course.shared.generated.resources.please_try_again_later
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val validateLoginFormUseCase: ValidateLoginFormUseCase,
    private val getDeviceIdUseCase: GetDeviceIdUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase,
) : ViewModel() {
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    private val _loginUiEvent = MutableStateFlow<LoginUiEvent>(LoginUiEvent.Idle)
    val loginUiEvent: StateFlow<LoginUiEvent> = _loginUiEvent.asStateFlow()

    fun updateEmail(email: String) = viewModelScope.launch {
        _loginUiState.update { state -> state.copy(email = email) }
    }
    fun updatePassword(password: String) = viewModelScope.launch {
        _loginUiState.update { state -> state.copy(password = password) }
    }
    fun updatePasswordVisible(passwordVisible: Boolean) = viewModelScope.launch {
        _loginUiState.update { state -> state.copy(passwordVisible = passwordVisible) }
    }

    fun validateForms(email: String, password: String) {
        if (_loginUiState.value.isLoading == StatusLoading.SHOW_LOADING ||
            _loginUiEvent.value == LoginUiEvent.LoginSuccess
        ) return
        val validationError = validateLoginFormUseCase(email, password)
        if (validationError != null) {
            val message = when (validationError) {
                LoginValidationError.REQUIRED_FIELDS -> Res.string.login_required_fields
                LoginValidationError.INVALID_EMAIL -> Res.string.login_invalid_email
            }
            _loginUiEvent.value = LoginUiEvent.ShowErrorDialog(message)
        } else {
            login(email = email, password = password)
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            flow {
                val deviceId = getDeviceIdUseCase()
                emitAll(loginUseCase.login(
                    url = NetworkUrl.LOGIN_ENDPOINT,
                    email = email,
                    password = password,
                    deviceId = deviceId,
                ))
            }
                .onStart {
                    _loginUiState.update { it.copy(isLoading = StatusLoading.SHOW_LOADING) }
                    _loginUiEvent.value = LoginUiEvent.Idle
                }.onEach { result ->
                    if (result is NetworkResult.Success) {
                        val data = result.response
                        saveUserTokenUseCase(
                            token = TokensModel(
                                accessToken = data.accessToken,
                                refreshToken = data.refreshToken,
                                tokenType = data.tokenType,
                                expiresIn = data.expiresIn,
                            ),
                        ).collect {}
                    }
                }.catch { exception ->
                    exception.printStackTrace()
                    _loginUiEvent.value = LoginUiEvent.ShowErrorDialog(Res.string.please_try_again_later)
                }.onCompletion {
                    _loginUiState.update { it.copy(isLoading = StatusLoading.DISMISS_LOADING) }
                }.collect { result ->
                    _loginUiEvent.value = when (result) {
                        is NetworkResult.Success -> LoginUiEvent.LoginSuccess
                        is NetworkResult.Error -> LoginUiEvent.ShowErrorStringDialog(result.message)
                    }
                }
        }
    }

    fun clearEvent() {
        _loginUiEvent.value = LoginUiEvent.Idle
    }
}
