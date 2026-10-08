package com.uagr.kmp.course.presentation.ui.register.viewmodel

import RegisterResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.usecase.register.RegisterUseCase
import com.uagr.kmp.course.domain.usecase.register.RegisterValidationError
import course.shared.generated.resources.Res
import course.shared.generated.resources.register_confirm_password_empty
import course.shared.generated.resources.register_email_empty
import course.shared.generated.resources.register_email_invalid
import course.shared.generated.resources.register_failed
import course.shared.generated.resources.register_name_empty
import course.shared.generated.resources.register_password_empty
import course.shared.generated.resources.register_password_mismatch
import course.shared.generated.resources.register_unknown_error
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<RegisterUIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onNameChanged(newValue: String) {
        _uiState.value = _uiState.value.copy(
            name = newValue,
            nameError = if (newValue.isNotBlank()) {
                null
            } else {
                _uiState.value.nameError
            },
            registerError = null
        )
    }

    fun onEmailChanged(newValue: String) {
        _uiState.value = _uiState.value.copy(
            email = newValue,
            emailError = if (newValue.isNotBlank()) {
                null
            } else {
                _uiState.value.emailError
            },
            registerError = null
        )
    }

    fun onPasswordChanged(newValue: String) {
        _uiState.value = _uiState.value.copy(
            password = newValue,
            passwordError = if (newValue.isNotBlank()) {
                null
            } else {
                _uiState.value.passwordError
            },
            registerError = null
        )
    }

    fun onConfirmPasswordChanged(newValue: String) {
        _uiState.value = _uiState.value.copy(
            confirmPassword = newValue,
            confirmPasswordError = if (newValue.isNotBlank()) {
                null
            } else {
                _uiState.value.confirmPasswordError
            },
            registerError = null
        )
    }

    fun onRegisterClicked() {

        _uiState.value = _uiState.value.copy(
            nameError = null,
            emailError = null,
            passwordError = null,
            confirmPasswordError = null,
            registerError = null,
            isLoading = true
        )

        viewModelScope.launch {
            try {

                val state = _uiState.value

                val result = registerUseCase(
                    name = state.name,
                    email = state.email,
                    password = state.password,
                    confirmPassword = state.confirmPassword
                )

                when (result) {

                    RegisterResult.Success -> {
                        _uiEvent.send(RegisterUIEvent.RegisterSuccess)
                    }

                    is RegisterResult.ValidationError -> {
                        _uiState.value = _uiState.value.copy(
                            nameError = result.nameError?.toMessage(),
                            emailError = result.emailError?.toMessage(),
                            passwordError = result.passwordError?.toMessage(),
                            confirmPasswordError = result.confirmPasswordError?.toMessage()
                        )
                    }

                    RegisterResult.RegisterFailed -> {
                        _uiState.value = _uiState.value.copy(
                            registerError = getString(Res.string.register_failed)
                        )
                    }
                }

            } catch (exception: Exception) {

                _uiState.value = _uiState.value.copy(
                    registerError = exception.message
                        ?: getString(Res.string.register_unknown_error)
                )

            } finally {

                _uiState.value = _uiState.value.copy(
                    isLoading = false
                )
            }
        }
    }
}

private suspend fun RegisterValidationError.toMessage(): String {
    return when (this) {
        RegisterValidationError.NAME_EMPTY ->
            getString(Res.string.register_name_empty)

        RegisterValidationError.EMAIL_EMPTY ->
            getString(Res.string.register_email_empty)

        RegisterValidationError.EMAIL_INVALID ->
            getString(Res.string.register_email_invalid)

        RegisterValidationError.PASSWORD_EMPTY ->
            getString(Res.string.register_password_empty)

        RegisterValidationError.CONFIRM_PASSWORD_EMPTY ->
            getString(Res.string.register_confirm_password_empty)

        RegisterValidationError.PASSWORD_MISMATCH ->
            getString(Res.string.register_password_mismatch)
    }
}