/*
 * RegisterViewModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.register.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.usecase.register.RegisterUserUseCase
import com.uagr.kmp.course.utils.operators.StatusLoading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private val _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState: StateFlow<RegisterUiState> = _registerUiState.asStateFlow()

    private val _registerUiEvent = MutableStateFlow<RegisterUiEvent>(RegisterUiEvent.Idle)
    val registerUiEvent: StateFlow<RegisterUiEvent> = _registerUiEvent.asStateFlow()

    fun updateName(name: String) {
        _registerUiState.update { it.copy(name = name) }
    }

    fun updateEmail(email: String) {
        _registerUiState.update { it.copy(email = email) }
    }

    fun updatePassword(password: String) {
        _registerUiState.update { it.copy(password = password) }
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _registerUiState.update { it.copy(confirmPassword = confirmPassword) }
    }

    fun validateRegisterForm(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        viewModelScope.launch {
            println(">>> INICIANDO REGISTRO con email: $email")
            _registerUiState.update {
                it.copy(isLoading = StatusLoading.SHOW_LOADING, errorDialog = null)
            }

            registerUserUseCase(
                name = name,
                email = email,
                password = password,
                confirmPassword = confirmPassword
            ).onSuccess {
                println(">>> REGISTRO EXITOSO")
                _registerUiState.update { it.copy(isLoading = StatusLoading.DISMISS_LOADING) }
                _registerUiEvent.value = RegisterUiEvent.RegisterSuccess
            }.onFailure { error ->
                println(">>> ERROR EN REGISTRO: ${error.message}")
                error.printStackTrace()
                _registerUiState.update {
                    it.copy(
                        isLoading = StatusLoading.DISMISS_LOADING,
                        errorDialog = ErrorDialogModel(
                            title = "Error de Registro",
                            message = error.message ?: "Ocurrió un error al registrar el usuario",
                            primaryButtonText = "Aceptar"
                        )
                    )
                }
            }
        }
    }

    fun dismissErrorDialog() {
        _registerUiState.update { it.copy(errorDialog = null) }
    }

    fun resetUiEvent() {
        _registerUiEvent.value = RegisterUiEvent.Idle
    }
}
