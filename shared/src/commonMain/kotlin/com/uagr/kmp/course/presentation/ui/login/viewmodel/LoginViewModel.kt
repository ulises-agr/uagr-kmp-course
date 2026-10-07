package com.uagr.kmp.course.presentation.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.usecase.login.LoginUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import com.uagr.kmp.course.domain.usecase.login.LoginResult

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()


    private val _uiEvent = Channel<LoginUIEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEmailChanged(newValue: String) {
        _uiState.value = _uiState.value.copy(
            email = newValue,
            emailError = if (newValue.isNotBlank()) null else _uiState.value.emailError,
            loginError = null
        )
    }

    fun onPasswordChanged(newValue: String) {
        _uiState.value = _uiState.value.copy(
            password = newValue,
            passwordError = if (newValue.isNotBlank()) null else _uiState.value.passwordError,
            loginError = null
        )
    }

    fun onLoginClicked() {

        _uiState.value = _uiState.value.copy(
            emailError = null,
            passwordError = null,
            loginError = null,
            isLoading = true
        )

        viewModelScope.launch {
            try {

                val result = loginUseCase(
                    email = _uiState.value.email,
                    password = _uiState.value.password
                )

                when (result) {
                    LoginResult.Success -> {
                        _uiEvent.send(LoginUIEvent.LoginSuccess)
                    }

                    LoginResult.InvalidCredentials -> {
                        _uiState.value = _uiState.value.copy(
                            loginError = "Correo o contraseña incorrectos"
                        )
                    }

                    LoginResult.EmptyEmail -> {
                        _uiState.value = _uiState.value.copy(
                            emailError = "Ingresa tu correo"
                        )
                    }

                    LoginResult.EmptyPassword -> {
                        _uiState.value = _uiState.value.copy(
                            passwordError = "Ingresa tu contraseña"
                        )
                    }

                    LoginResult.EmptyEmailAndPassword -> {
                        _uiState.value = _uiState.value.copy(
                            emailError = "Ingresa tu correo",
                            passwordError = "Ingresa tu contraseña"
                        )
                    }
                }

            } catch (exception: Exception) {
                println("LOGIN ERROR -> ${exception.message}")
            } finally {
                _uiState.value = _uiState.value.copy(
                    isLoading = false
                )
            }
        }
    }
}