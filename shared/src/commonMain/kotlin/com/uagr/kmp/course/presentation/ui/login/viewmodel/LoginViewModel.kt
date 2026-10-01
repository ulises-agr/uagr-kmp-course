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

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _emailError = MutableStateFlow<String?>(null)
    private val _passwordError = MutableStateFlow<String?>(null)
    private val _loginError = MutableStateFlow<String?>(null)

    val email: StateFlow<String> = _email.asStateFlow()
    val password: StateFlow<String> = _password.asStateFlow()
    val emailError: StateFlow<String?> = _emailError.asStateFlow()
    val passwordError: StateFlow<String?> = _passwordError.asStateFlow()
    val loginError: StateFlow<String?> = _loginError.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private val _uiEvent = Channel<LoginUIEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEmailChanged(newValue: String) {
        _email.value = newValue

        if (newValue.isNotBlank()) {
            _emailError.value = null
        }

        _loginError.value = null
    }

    fun onPasswordChanged(newValue: String) {
        _password.value = newValue

        if (newValue.isNotBlank()) {
            _passwordError.value = null
        }

        _loginError.value = null
    }

    fun onLoginClicked() {

        _emailError.value = null
        _passwordError.value = null

        if (_email.value.isBlank()) {
            _emailError.value = "Ingresa tu correo"
        }

        if (_password.value.isBlank()) {
            _passwordError.value = "Ingresa tu contraseña"
        }

        if (_emailError.value != null || _passwordError.value != null) {
            return
        }

        _isLoading.value = true

        viewModelScope.launch {

            try {

                val success = loginUseCase(
                    email = _email.value,
                    password = _password.value
                )

                if (success) {
                    _loginError.value = null
                    _uiEvent.send(LoginUIEvent.LoginSuccess)
                } else {
                    _loginError.value = "Correo o contraseña incorrectos"
                    _uiEvent.send(LoginUIEvent.InvalidCredentials)
                }

            } catch (exception: Exception) {

                println("LOGIN ERROR -> ${exception.message}")

            } finally {

                _isLoading.value = false
            }
        }
    }
}