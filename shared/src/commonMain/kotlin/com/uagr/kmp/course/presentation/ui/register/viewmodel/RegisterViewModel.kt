package com.uagr.kmp.course.presentation.ui.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.model.register.RegisterDataModel
import com.uagr.kmp.course.domain.usecase.register.RegisterUseCase
import com.uagr.kmp.course.domain.usecase.register.RegisterValidationResult
import com.uagr.kmp.course.domain.usecase.register.ValidateRegisterFormUseCase
import com.uagr.kmp.course.domain.usecase.user.InsertUserAndDeleteUseCase
import com.uagr.kmp.course.domain.usecase.user.SaveUserTokenUseCase
import com.uagr.kmp.course.utils.constant.NetworkUrl
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.operators.StatusLoading
import course.shared.generated.resources.Res
import course.shared.generated.resources.accept
import course.shared.generated.resources.confirm_password_empty
import course.shared.generated.resources.email_empty
import course.shared.generated.resources.error
import course.shared.generated.resources.name_empty
import course.shared.generated.resources.not_matching_passwords
import course.shared.generated.resources.password_empty
import course.shared.generated.resources.please_try_again_later
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class RegisterViewModel(
    private val validateRegisterFormUseCase: ValidateRegisterFormUseCase,
    private val registerUseCase: RegisterUseCase,
    private val insertUserAndDeleteUseCase: InsertUserAndDeleteUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase,
) : ViewModel() {

    private var _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState: StateFlow<RegisterUiState> = _registerUiState.asStateFlow()

    private var _registerUiEvent = MutableStateFlow<RegisterUiEvent>(RegisterUiEvent.Idle)
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

    fun updatePasswordVisible(passwordVisible: Boolean) = viewModelScope.launch {
        _registerUiState.update { state -> state.copy(passwordVisible = passwordVisible) }
    }

    fun updateConfirmPassword(password: String) = viewModelScope.launch {
        _registerUiState.update { state -> state.copy(confirmPassword = password) }
    }

    fun updateConfirmPasswordVisible(passwordVisible: Boolean) = viewModelScope.launch {
        _registerUiState.update { state -> state.copy(confirmPasswordVisible = passwordVisible) }
    }


    fun validateRegisterForm(
        email: String,
        password: String,
        name: String,
        confirmPass: String,
    ) = viewModelScope.launch {
        when (validateRegisterFormUseCase(
            email = email,
            password = password,
            name = name,
            confirmPassword = confirmPass,
        )) {
            is RegisterValidationResult.EmailEmpty -> {
                _registerUiState.update { state -> state.copy(errorDialog = setErrorDialog(message = getString(Res.string.email_empty))) }
            }
            is RegisterValidationResult.PasswordEmpty -> {
                _registerUiState.update { state -> state.copy(errorDialog = setErrorDialog(message = getString(Res.string.password_empty))) }
            }
            is RegisterValidationResult.NameEmpty -> {
                _registerUiState.update { state -> state.copy(errorDialog = setErrorDialog(message = getString(Res.string.name_empty))) }
            }
            is RegisterValidationResult.ConfirmPasswordEmpty -> {
                _registerUiState.update { state -> state.copy(errorDialog = setErrorDialog(message = getString(Res.string.confirm_password_empty))) }
            }
            is RegisterValidationResult.DifferentPasswords -> {
                _registerUiState.update { state -> state.copy(errorDialog = setErrorDialog(message = getString(Res.string.not_matching_passwords))) }
            }

            is RegisterValidationResult.Success -> {
                register(
                    email = email,
                    password = password,
                    name = name,
                )
            }
        }
    }

    private fun register(
        email: String,
        password: String,
        name: String,
    ) = viewModelScope.launch {
        registerUseCase.register(
            url = NetworkUrl.REGISTER_ENDPOINT,
            email = email,
            password = password,
            name = name,
        ).onStart {
            _registerUiState.update { state -> state.copy(isLoading = StatusLoading.SHOW_LOADING) }
        }.catch {
            _registerUiState.update { state ->
                state.copy(
                    isLoading = StatusLoading.DISMISS_LOADING,
                    errorDialog = setErrorDialog(),
                )
            }
        }.collect { result ->
            when (result) {
                is NetworkResult.Success -> {
                    result.response.data?.let { data ->
                        insertUserAndDelete(registerData = data)
                    } ?: run {
                        _registerUiState.update { state ->
                            state.copy(
                                isLoading = StatusLoading.DISMISS_LOADING,
                                errorDialog = setErrorDialog(),
                            )
                        }
                    }
                }
                is NetworkResult.Error -> {
                    _registerUiState.update { state ->
                        state.copy(
                            isLoading = StatusLoading.DISMISS_LOADING,
                            errorDialog = setErrorDialog(message = result.message),
                        )
                    }
                }
            }
        }
    }

    private fun insertUserAndDelete(registerData: RegisterDataModel) = viewModelScope.launch {
        insertUserAndDeleteUseCase(user = registerData.user)
            .catch { exception ->
                exception.printStackTrace()
                _registerUiState.update { state ->
                    state.copy(
                        isLoading = StatusLoading.DISMISS_LOADING,
                        errorDialog = setErrorDialog(message = exception.message),
                    )
                }
            }.collect {
                saveUserToken(registerData = registerData)
            }
    }

    private fun saveUserToken(registerData: RegisterDataModel) = viewModelScope.launch {
        saveUserTokenUseCase(token = registerData.tokens)
            .catch { exception ->
                exception.printStackTrace()
                _registerUiState.update { state ->
                    state.copy(
                        isLoading = StatusLoading.DISMISS_LOADING,
                        errorDialog = setErrorDialog(message = exception.message),
                    )
                }
            }.collect {
                _registerUiEvent.emit(RegisterUiEvent.RegisterSuccess)
            }
    }

    private suspend fun setErrorDialog(message: String? = null): ErrorDialogModel =
        ErrorDialogModel(
            title = getString(resource = Res.string.error),
            message = message ?: getString(resource = Res.string.please_try_again_later),
            primaryButtonText = getString(resource = Res.string.accept),
        )

    fun dismissErrorDialog() = viewModelScope.launch {
        _registerUiState.update { state -> state.copy(errorDialog = null) }
    }

    fun resetUiEvent() = viewModelScope.launch {
        _registerUiEvent.value = RegisterUiEvent.Idle
    }
}
