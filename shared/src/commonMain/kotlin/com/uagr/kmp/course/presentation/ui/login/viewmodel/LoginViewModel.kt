package com.uagr.kmp.course.presentation.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _emailError = MutableStateFlow<String?>(null)

    private val _passwordError = MutableStateFlow<String?>(null)

    val email: StateFlow<String> = _email.asStateFlow()
    val password: StateFlow<String> = _password.asStateFlow()
    val emailError: StateFlow<String?> = _emailError.asStateFlow()
    val passwordError: StateFlow<String?> = _passwordError.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private val _uiEvent = Channel<LoginUIEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEmailChanged(newValue: String) {
        _email.value = newValue

        if (newValue.isNotBlank()) {
            _emailError.value = null
        }
    }

    fun onPasswordChanged(newValue: String) {
        _password.value = newValue

        if (newValue.isNotBlank()) {
            _passwordError.value = null
        }
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
            println("LOGIN -> email: ${_email.value}")

            _isLoading.value = false
            _uiEvent.send(LoginUIEvent.LoginSuccess)
        }
    }
}