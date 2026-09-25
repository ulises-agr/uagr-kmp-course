/*
 * LoginViewModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.data.network.model.packages.response.PackagesResponse
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.model.login.LoginDataModel
import com.uagr.kmp.course.domain.model.login.UserTokensModel
import com.uagr.kmp.course.domain.model.packages.PackageModel
import com.uagr.kmp.course.domain.usecase.login.LoginUseCase
import com.uagr.kmp.course.domain.usecase.login.ValidateLoginFormResult
import com.uagr.kmp.course.domain.usecase.login.ValidateLoginFormUseCase
import com.uagr.kmp.course.domain.usecase.user.InsertUserUseCase
import com.uagr.kmp.course.domain.usecase.user.SaveUserTokenUseCase
import com.uagr.kmp.course.utils.constants.NetworkUrl
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.operators.StatusLoading
import course.shared.generated.resources.Res
import course.shared.generated.resources.accept
import course.shared.generated.resources.email_empty
import course.shared.generated.resources.email_format
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
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.getString
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(
    private val validateLoginFormUseCase: ValidateLoginFormUseCase,
    private val loginUseCase: LoginUseCase,
    private val insertUserUseCase : InsertUserUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase,
) : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState : StateFlow<LoginUiState> = _loginUiState.asStateFlow()
    
    private val _loginUiEvent = MutableStateFlow<LoginUIEvent>(LoginUIEvent.Idle)
    val loginUiEvent : StateFlow<LoginUIEvent> = _loginUiEvent.asStateFlow()
    
    fun updatePassword(password : String){
        _loginUiState.update { state -> state.copy(password = password, passwordError = "") }
    }
    
    fun updateEmail(email : String){
        _loginUiState.update{state -> state.copy(email = email, emailError = "")}
    }
    
    
    fun validateLoginForm(
        email: String,
        password: String,
    ) = viewModelScope.launch {
        when(validateLoginFormUseCase(
            email = email,
            password = password,
        )){
            is ValidateLoginFormResult.EmptyFields ->{
                _loginUiState.update { state -> state.copy(
                    emailError = getString(Res.string.email_empty),
                    passwordError = getString(Res.string.password_empty)
                ) }
            }
            is ValidateLoginFormResult.EmailEmpty ->{
                _loginUiState.update { state -> state.copy(emailError = getString(Res.string.email_empty) ) }
            }
            is ValidateLoginFormResult.WrongEmailFormat -> {
                _loginUiState.update { state -> state.copy(emailError = getString(Res.string.email_format)) }
            }
            is ValidateLoginFormResult.PasswordEmpty -> {
                _loginUiState.update { state -> state.copy(passwordError = getString(Res.string.password_empty)) }
            }
            else -> {
                login(email = email, password = password)
            }
        }
    }
    
    fun login(
        email: String,
        password: String,
    ) = viewModelScope.launch{
        loginUseCase.login(
            url = NetworkUrl.LOGIN_ENDPOINT,
            email = email,
            password = password
        ).onStart {
            _loginUiState.update { state -> state.copy(isLoading = StatusLoading.SHOW_LOADING) }
        }.catch {
            _loginUiState.update { state -> state.copy(
                isLoading = StatusLoading.HIDE_LOADING,
                errorDialog = setError()
            ) }
        }.collect { result ->
            when(result){
                is NetworkResult.Success -> {
                    result.response.data?.let{ loginData ->
                        insertUser(
                            loginData = loginData
                        )
                    } ?: run {
                        _loginUiState.update { state -> state.copy(
                            isLoading = StatusLoading.HIDE_LOADING,
                            errorDialog = setError()
                        ) }
                    }
                }
                is NetworkResult.Error -> {
                    _loginUiState.update { state -> state.copy(
                        isLoading = StatusLoading.HIDE_LOADING,
                        errorDialog = setError()
                    ) }
                }
            }
        }
    }
    
    private fun insertUser(loginData : LoginDataModel) = viewModelScope.launch{
        insertUserUseCase(user = loginData.user)
            .catch {
                _loginUiState.update { state ->
                    state.copy(
                        isLoading = StatusLoading.HIDE_LOADING,
                        errorDialog = setError()
                    )
                }
            }
            .collect {
                saveUserToken(tokenData = loginData.tokens)
            }
    }
    
    private fun saveUserToken(tokenData : UserTokensModel?) = viewModelScope.launch {
        saveUserTokenUseCase(token = tokenData)
            .catch {
                _loginUiState.update { state ->
                    state.copy(
                        isLoading = StatusLoading.HIDE_LOADING,
                        errorDialog = setError()
                    )
                }
            }
            .collect {
                _loginUiEvent.emit(LoginUIEvent.LoginSuccess)
            }
        
    }
    
    fun onPasswordVisibilityChanged(isVisible : Boolean) = viewModelScope.launch{
        _loginUiState.update { state -> state.copy(passwordVisible =  isVisible) }
    }
    
    private suspend fun setError(message : String? = null) : ErrorDialogModel =
        ErrorDialogModel(
            title = getString(resource = Res.string.error),
            message = message ?: getString(resource = Res.string.please_try_again_later),
            primaryButtonText = getString(resource = Res.string.accept),
        )
    
    fun dismissDialog(){
        _loginUiState.update { state -> state.copy(errorDialog =  null) }
    }
    
    fun resetUIEvent() = viewModelScope.launch{
        _loginUiEvent.value = LoginUIEvent.Idle
    }
    
}