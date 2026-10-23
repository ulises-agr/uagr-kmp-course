/*
 * LoginViewModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.presentation.welcome.viewmodel.LoginUiEvent
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.model.login.TokensModel
import com.uagr.kmp.course.domain.usecase.login.LoginValidateResult
import com.uagr.kmp.course.domain.usecase.login.ValidateLoginFormUseCase
import com.uagr.kmp.course.domain.usecase.user.GetUserCase
import com.uagr.kmp.course.domain.usecase.user.LoginUseCase
import com.uagr.kmp.course.domain.usecase.user.SaveTokenUseCase
import com.uagr.kmp.course.utils.constant.NetworkUrl
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.operators.StatusLoading
import course.shared.generated.resources.Res
import course.shared.generated.resources.accept
import course.shared.generated.resources.email_empty
import course.shared.generated.resources.error
import course.shared.generated.resources.password_empty
import course.shared.generated.resources.please_try_again_later
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val getUserCase: GetUserCase,
    private val validateLoginFormUseCase: ValidateLoginFormUseCase,
): ViewModel() {
    private var _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    private var _loginUiEvent = MutableStateFlow<LoginUiEvent>(LoginUiEvent.Idle)
    val loginUiEvent: StateFlow<LoginUiEvent> = _loginUiEvent.asStateFlow()

    fun updateEmail(email: String) = viewModelScope.launch {
        _loginUiState.update { state -> state.copy(email = email) }
    }
    fun resetUiEvent() = viewModelScope.launch {
        _loginUiEvent.value = LoginUiEvent.Idle
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
        )){
            is LoginValidateResult.EmailEmpty -> {
                _loginUiState.update { state -> state.copy(errorDialog = setErrorDialog(message = getString(Res.string.email_empty))) }
            }
            is LoginValidateResult.PasswordEmpty -> {
                _loginUiState.update { state -> state.copy(errorDialog = setErrorDialog(message = getString(Res.string.password_empty))) }
            }
            is LoginValidateResult.Success -> {
                val user = getUserCase.invoke()
                if (user != null){
                    login(
                        email = loginUiState.value.email,
                        password = loginUiState.value.password,
                        id = user.id
                    )
                }
            }
        }
    }

    private fun login(
        email: String,
        password: String,
        id: String,
    ) = viewModelScope.launch {

        loginUseCase.invoke(
            url = NetworkUrl.LOGIN_ENDPOINT,
            email = email,
            password = password,
            deviceId = id,
        ).onStart {
            _loginUiState.update { state -> state.copy(isLoading = StatusLoading.SHOW_LOADING) }
        }.catch {
            _loginUiState.update { state ->
                state.copy(
                    isLoading = StatusLoading.DISMISS_LOADING,
                    errorDialog = setErrorDialog()
                )
            }
        }.collect { result ->
            when(result){
                is NetworkResult.Error -> {
                    _loginUiState.update { state ->
                        state.copy(
                            isLoading = StatusLoading.DISMISS_LOADING,
                            errorDialog = setErrorDialog()
                        )
                    }
                }
                is NetworkResult.Success -> {
                    result.response?.let { data ->
                        insertTokenAndDelete(data)
                    } ?: run {
                        _loginUiState.update { state ->
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
    //Guardado
    private suspend fun insertTokenAndDelete(
        loginInfo: TokensModel
    ){
        saveTokenUseCase(token = loginInfo)
            .catch {
                _loginUiState.update { state ->
                    state.copy(
                        isLoading = StatusLoading.DISMISS_LOADING,
                        errorDialog = setErrorDialog(),
                    )
                }
            }.collect {
                saveUserToken(token = loginInfo)
            }
    }
    private fun saveUserToken(token : TokensModel) = viewModelScope.launch {
        saveTokenUseCase(token)
            .catch {
                _loginUiState.update { state ->
                    state.copy(
                        isLoading = StatusLoading.DISMISS_LOADING,
                        errorDialog = setErrorDialog(),
                    )
                }
            }.collect {
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
}
