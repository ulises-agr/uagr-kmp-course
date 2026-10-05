package com.uagr.kmp.course.presentation.ui.register.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.uagr.kmp.course.domain.usecase.register.RegisterUseCase
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _event = Channel<RegisterUIEvent>()
    val event = _event.receiveAsFlow()
    private val _name = MutableStateFlow("")
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _confirmPassword = MutableStateFlow("")
    private val _isLoading = MutableStateFlow(false)

    private val _nameError = MutableStateFlow<String?>(null)
    private val _emailError = MutableStateFlow<String?>(null)
    private val _passwordError = MutableStateFlow<String?>(null)
    private val _confirmPasswordError = MutableStateFlow<String?>(null)

    val name: StateFlow<String> = _name.asStateFlow()
    val email: StateFlow<String> = _email.asStateFlow()
    val password: StateFlow<String> = _password.asStateFlow()
    val confirmPassword: StateFlow<String> = _confirmPassword.asStateFlow()
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()


    val nameError: StateFlow<String?> = _nameError.asStateFlow()
    val emailError: StateFlow<String?> = _emailError.asStateFlow()
    val passwordError: StateFlow<String?> = _passwordError.asStateFlow()
    val confirmPasswordError: StateFlow<String?> = _confirmPasswordError.asStateFlow()

    fun onNameChanged(newValue: String) {
        _name.value = newValue

        if (newValue.isNotBlank()) {
            _nameError.value = null
        }
    }

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

    fun onConfirmPasswordChanged(newValue: String) {
        _confirmPassword.value = newValue

        if (newValue.isNotBlank()) {
            _confirmPasswordError.value = null
        }
    }

    fun onRegisterClicked() {

        _nameError.value = null
        _emailError.value = null
        _passwordError.value = null
        _confirmPasswordError.value = null

        if (_name.value.isBlank()) {
            _nameError.value = "Ingresa tu nombre"
        }

        if (_email.value.isBlank()) {
            _emailError.value = "Ingresa tu correo"
        } else if (!isValidEmail(_email.value)) {
            _emailError.value = "Ingresa un correo valido"
        }

        if (_password.value.isBlank()) {
            _passwordError.value = "Ingresa tu contraseña"
        }

        if (_confirmPassword.value.isBlank()) {
            _confirmPasswordError.value = "Confirma tu contraseña"
        } else if (_password.value != _confirmPassword.value) {
            _confirmPasswordError.value = "Las contraseñas no coinciden"
        }

        if (
            _nameError.value != null ||
            _emailError.value != null ||
            _passwordError.value != null ||
            _confirmPasswordError.value != null
        ) {
            return
        }

        viewModelScope.launch {

            _isLoading.value = true

            try {

                val success = registerUseCase(
                    name = _name.value,
                    email = _email.value,
                    password = _password.value
                )

                if (success) {
                    println("REGISTER SUCCESS")
                    _event.send(RegisterUIEvent.RegisterSuccess)
                } else {
                    _event.send(
                        RegisterUIEvent.RegisterError(
                            message = "No fue posible registrar al usuario"
                        )
                    )
                }

            } catch (exception: Exception) {
                _event.send(
                    RegisterUIEvent.RegisterError(
                        message = exception.message ?: "Ocurrio un error"
                    )
                )
            } finally {

                _isLoading.value = false
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }
}