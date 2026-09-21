/*
 * LoginViewModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.usecase.login.ValidateLoginFormResult
import com.uagr.kmp.course.domain.usecase.login.ValidateLoginFormUseCase
import com.uagr.kmp.course.utils.operators.StatusLoading
import course.shared.generated.resources.Res
import course.shared.generated.resources.accept
import course.shared.generated.resources.email_empty
import course.shared.generated.resources.email_format
import course.shared.generated.resources.error
import course.shared.generated.resources.login
import course.shared.generated.resources.password_empty
import course.shared.generated.resources.please_try_again_later
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource

class LoginViewModel(
    private val validateLoginFormUseCase : ValidateLoginFormUseCase
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
        email : String,
        password : String
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
                _loginUiState.update { state -> state.copy(isLoading = StatusLoading.SHOW_LOADING) }
            }
        }
    }
    
    fun login(){
    
    }
    
    fun onPasswordVisibilityChanged(isVisible : Boolean) = viewModelScope.launch{
        _loginUiState.update { state -> state.copy(passwordVisible =  isVisible) }
    }
    
    private suspend fun setError(message : String) : ErrorDialogModel =
        ErrorDialogModel(
            title = getString(resource = Res.string.error),
            message = message,
            primaryButtonText = getString(resource = Res.string.please_try_again_later),
            secondaryButtonText = getString(resource = Res.string.accept),
        )
    
}