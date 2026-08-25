/*
 * DashboardViewModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.model.login.LoginTokensModel
import com.uagr.kmp.course.domain.usecase.login.LoginUseCase
import com.uagr.kmp.course.domain.usecase.login.ValidateLoginUseCase
import com.uagr.kmp.course.domain.usecase.login.ValidationResult
import com.uagr.kmp.course.domain.usecase.user.ClearAndInsertUserUseCase
import com.uagr.kmp.course.domain.usecase.user.SaveUserTokenUseCase
import com.uagr.kmp.course.util.enums.StatusLoading
import com.uagr.kmp.course.util.network.NetworkClient
import com.uagr.kmp.course.util.network.api.NetworkResult
import course.shared.generated.resources.Res
import course.shared.generated.resources.email_empty
import course.shared.generated.resources.password_empty
import course.shared.generated.resources.please_try_again_later
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val validateLoginUseCase: ValidateLoginUseCase,
    private val loginUseCase: LoginUseCase,
    private val clearAndInsertUserUseCase: ClearAndInsertUserUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase,
) : ViewModel() {

    private var _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    private var _loginUiEvent = MutableStateFlow<LoginUiEvent>(LoginUiEvent.Idle)
    val loginUiEvent: StateFlow<LoginUiEvent> = _loginUiEvent.asStateFlow()

    fun updateEmail(email: String) = viewModelScope.launch  {
        _loginUiState.update { state -> state.copy(email = email) }
    }

    fun updatePassword(password: String) = viewModelScope.launch  {
        _loginUiState.update { state -> state.copy(password = password) }
    }

    fun loginValidation(
        email: String,
        password: String,
    ) = viewModelScope.launch {
        when (validateLoginUseCase(email = email, password = password)) {
            is ValidationResult.EmailEmpty -> {
                _loginUiEvent.emit(LoginUiEvent.ShowErrorDialog(message = Res.string.email_empty))
            }
            is ValidationResult.PasswordEmpty -> {
                _loginUiEvent.emit(LoginUiEvent.ShowErrorDialog(message = Res.string.password_empty))
            }
            is ValidationResult.ValidationSuccess -> {
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
            url = NetworkClient.LOGIN_ENDPOINT,
            email = email,
            password = password,
        ).onStart {
            _loginUiState.update { state -> state.copy(isLoading = StatusLoading.SHOW_LOADING) }
        }.catch {
            _loginUiEvent.emit(LoginUiEvent.ShowErrorDialog(message = Res.string.please_try_again_later))
            _loginUiState.update { state -> state.copy(isLoading = StatusLoading.DISMISS_LOADING) }
        }.collect { result ->
            _loginUiState.update { state -> state.copy(isLoading = StatusLoading.DISMISS_LOADING) }
            when (result) {
                is NetworkResult.Success -> {
                    result.response.data?.let { data ->
                        insertUsers(
                            tokens = data.tokens,
                            email = email,
                            password = password,
                        )
                    } ?: run {
                        _loginUiEvent.emit(LoginUiEvent.ShowErrorDialog(message = Res.string.please_try_again_later))
                    }
                }
                is NetworkResult.Error -> {
                    _loginUiEvent.emit(LoginUiEvent.ShowErrorDialog(message = Res.string.please_try_again_later))
                }
            }
        }
    }

    private fun insertUsers(
        tokens: LoginTokensModel?,
        email: String,
        password: String,
    ) = viewModelScope.launch {
        clearAndInsertUserUseCase.clearAndInsertUser(email = email, password = password)
            .catch {
                _loginUiEvent.emit(LoginUiEvent.ShowErrorDialog(message = Res.string.please_try_again_later))
                _loginUiState.update { state -> state.copy(isLoading = StatusLoading.DISMISS_LOADING) }
            }.collect {
                saveUserToken(token = tokens?.accessToken.orEmpty())
            }
    }

    private fun saveUserToken(token: String) = viewModelScope.launch {
        saveUserTokenUseCase.saveUserToken(token = token)
            .catch {
                _loginUiEvent.emit(LoginUiEvent.ShowErrorDialog(message = Res.string.please_try_again_later))
                _loginUiState.update { state -> state.copy(isLoading = StatusLoading.DISMISS_LOADING) }
            }.collect {
                _loginUiEvent.emit(LoginUiEvent.LoginSuccess)
            }
    }

    fun resetUiEvent() = viewModelScope.launch  {
        _loginUiEvent.value = LoginUiEvent.Idle
    }
}
