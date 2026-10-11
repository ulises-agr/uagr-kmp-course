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
import com.uagr.kmp.course.domain.usecase.login.LoginValidationResult
import com.uagr.kmp.course.domain.usecase.login.ValidateLoginUseCase
import course.shared.generated.resources.Res
import course.shared.generated.resources.login_invalid_credentials
import course.shared.generated.resources.register_email_empty
import course.shared.generated.resources.register_email_invalid
import course.shared.generated.resources.register_password_empty
import org.jetbrains.compose.resources.getString
import kotlin.coroutines.cancellation.CancellationException

class LoginViewModel(
    private val validateLoginUseCase: ValidateLoginUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

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
            loginError = null
        )

        viewModelScope.launch {

            val email = _uiState.value.email
            val password = _uiState.value.password

            val validationResult = validateLoginUseCase(
                email = email,
                password = password
            )

            when (validationResult) {

                LoginValidationResult.EmptyEmail -> {
                    _uiState.value = _uiState.value.copy(
                        emailError = getString(Res.string.register_email_empty)
                    )
                }

                LoginValidationResult.InvalidEmail -> {
                    _uiState.value = _uiState.value.copy(
                        emailError = getString(Res.string.register_email_invalid)
                    )
                }

                LoginValidationResult.EmptyPassword -> {
                    _uiState.value = _uiState.value.copy(
                        passwordError = getString(Res.string.register_password_empty)
                    )
                }

                LoginValidationResult.EmptyEmailAndPassword -> {
                    _uiState.value = _uiState.value.copy(
                        emailError = getString(Res.string.register_email_empty),
                        passwordError = getString(Res.string.register_password_empty)
                    )
                }

                LoginValidationResult.ValidationPassed -> {

                    _uiState.value = _uiState.value.copy(
                        isLoading = true
                    )

                    try {
                        val result = loginUseCase(
                            email = email,
                            password = password
                        )

                        when (result) {

                            LoginResult.Success -> {
                                _uiEvent.send(LoginUIEvent.LoginSuccess)
                            }

                            LoginResult.InvalidCredentials -> {
                                _uiState.value = _uiState.value.copy(
                                    loginError = getString(
                                        Res.string.login_invalid_credentials
                                    )
                                )
                            }
                        }

                    } catch (exception: Exception) {

                        if (exception is CancellationException) {
                            throw exception
                        }

                        println("LOGIN ERROR -> ${exception.message}")

                    } finally {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}