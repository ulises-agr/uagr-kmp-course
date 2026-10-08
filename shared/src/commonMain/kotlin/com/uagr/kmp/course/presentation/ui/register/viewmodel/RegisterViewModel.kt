/*
// RegisterViewModel.kt
// Created by Karla Judith Cervantes Higareda on 01/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.usecase.register.RegisterUseCase
import com.uagr.kmp.course.domain.usecase.register.RegisterValidationError
import com.uagr.kmp.course.domain.usecase.register.ValidateRegisterFormUseCase
import com.uagr.kmp.course.utils.constant.NetworkUrl
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.operators.StatusLoading
import course.shared.generated.resources.Res
import course.shared.generated.resources.please_try_again_later
import course.shared.generated.resources.register_required_fields
import course.shared.generated.resources.register_invalid_email
import course.shared.generated.resources.register_password_mismatch
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
    private val validateRegisterFormUseCase: ValidateRegisterFormUseCase,
) : ViewModel() {
    private val _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState: StateFlow<RegisterUiState> = _registerUiState.asStateFlow()

    private val _registerUiEvent = MutableStateFlow<RegisterUiEvent>(RegisterUiEvent.Idle)
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
    fun updatePasswordVisible(passwordVisible: Boolean) = viewModelScope.launch {
        _registerUiState.update { state -> state.copy(passwordVisible = passwordVisible) }
    }
    fun updateConfirmPasswordVisible(confirmPasswordVisible: Boolean) = viewModelScope.launch {
        _registerUiState.update { state -> state.copy(confirmPasswordVisible = confirmPasswordVisible) }
    }
    fun validateForms(name: String, email: String, password: String, confirmPassword: String) {
        val validationError = validateRegisterFormUseCase(name, email, password, confirmPassword)
        if (validationError != null) {
            val message = when (validationError) {
                RegisterValidationError.REQUIRED_FIELDS -> Res.string.register_required_fields
                RegisterValidationError.INVALID_EMAIL -> Res.string.register_invalid_email
                RegisterValidationError.PASSWORD_MISMATCH -> Res.string.register_password_mismatch
            }
            _registerUiEvent.value = RegisterUiEvent.ShowErrorDialog(message)
            return
        }
        register(name = name, email = email, password = password)
    }
    private fun register(name: String, email: String, password: String) {
        if (_registerUiState.value.isLoading == StatusLoading.SHOW_LOADING ||
            _registerUiEvent.value == RegisterUiEvent.Registered
        ) return

        _registerUiState.update { it.copy(isLoading = StatusLoading.SHOW_LOADING) }
        viewModelScope.launch {
            registerUseCase.register(url = NetworkUrl.REGISTER_ENDPOINT, name = name, email = email, password = password)
                .onStart {
                    _registerUiEvent.value = RegisterUiEvent.Idle
                }.catch { exception ->
                    if (exception is CancellationException) throw exception
                    _registerUiEvent.value = RegisterUiEvent.ShowErrorDialog(Res.string.please_try_again_later)
                }.onCompletion {
                    _registerUiState.update { it.copy(isLoading = StatusLoading.DISMISS_LOADING) }
                }.collect { result ->
                    _registerUiEvent.value = when (result) {
                        is NetworkResult.Success -> RegisterUiEvent.Registered
                        is NetworkResult.Error -> RegisterUiEvent.ShowErrorStringDialog(result.message)
                    }
                }
        }
    }

    fun clearEvent() {
        _registerUiEvent.value = RegisterUiEvent.Idle
    }
}
