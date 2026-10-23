package com.uagr.kmp.course.presentation.ui.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.presentation.welcome.viewmodel.LoginUiEvent
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.model.login.TokensModel
import com.uagr.kmp.course.domain.model.register.UserModel
import com.uagr.kmp.course.domain.usecase.user.SaveTokenUseCase
import com.uagr.kmp.course.domain.usecase.register.RegisterUseCase
import com.uagr.kmp.course.domain.usecase.register.ValidateRegisterFormUseCase
import com.uagr.kmp.course.domain.usecase.register.ValidateRegisterResult
import com.uagr.kmp.course.domain.usecase.user.SaveUserUseCase
import com.uagr.kmp.course.utils.constant.NetworkUrl
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.operators.StatusLoading
import course.shared.generated.resources.Res
import course.shared.generated.resources.accept
import course.shared.generated.resources.confirm_password_empty
import course.shared.generated.resources.email_empty
import course.shared.generated.resources.error
import course.shared.generated.resources.name_empty
import course.shared.generated.resources.password_empty
import course.shared.generated.resources.pasword_not_same
import course.shared.generated.resources.please_try_again_later
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val validateRegisterFormUseCase: ValidateRegisterFormUseCase,
): ViewModel(){
    private var _registerUiState = MutableStateFlow<RegisterUiState>(RegisterUiState())
    val registerUiState : StateFlow<RegisterUiState> = _registerUiState.asStateFlow()
    private var _registerUiEvent = MutableStateFlow<RegisterUiEvent>(RegisterUiEvent.Idle)
    val registerUiEvent: StateFlow<RegisterUiEvent> = _registerUiEvent.asStateFlow()

    fun showInfoDialog() = viewModelScope.launch {
        _registerUiEvent.emit(RegisterUiEvent.ShowVersionInfoDialog)
    }

    fun resetUiEvent() = viewModelScope.launch {
        _registerUiEvent.emit(RegisterUiEvent.Idle)
    }
    fun updateEmail(email: String) = viewModelScope.launch {
        _registerUiState.update { state -> state.copy(email = email) }
    }
    fun updateName(name: String) = viewModelScope.launch {
        _registerUiState.update { state -> state.copy(name = name) }
    }
    fun updatePassword(password: String) = viewModelScope.launch {
        _registerUiState.update { state -> state.copy(password = password) }
    }
    fun updateRepeatPassword(repeatpassword: String) = viewModelScope.launch {
        _registerUiState.update { state -> state.copy(repeatpassword = repeatpassword) }
    }
    fun validateRegisterForm(
        name: String,
        email: String,
        password: String,
        repeatpassword: String
    ) = viewModelScope.launch {
        when(validateRegisterFormUseCase(
            name = name,
            email = email,
            password = password,
            passwordConfirm = repeatpassword,
        )){
            is ValidateRegisterResult.NameEmpty -> {
                _registerUiState.update { state -> state.copy(errorDialog = setErrorDialog(message = getString(Res.string.name_empty))) }
            }
            is ValidateRegisterResult.EmailEmpty -> {
                _registerUiState.update { state -> state.copy(errorDialog = setErrorDialog(message = getString(Res.string.email_empty))) }
            }
            is ValidateRegisterResult.PasswordEmpty -> {
                _registerUiState.update { state -> state.copy(errorDialog = setErrorDialog(message = getString(Res.string.password_empty))) }
            }
            is ValidateRegisterResult.PasswordConfirmEmpty -> {
                _registerUiState.update { state -> state.copy(errorDialog = setErrorDialog(message = getString(Res.string.confirm_password_empty))) }
            }
            is ValidateRegisterResult.IsNotSamePassword -> {
                _registerUiState.update { state -> state.copy(errorDialog = setErrorDialog(message = getString(Res.string.pasword_not_same))) }
            }
            is ValidateRegisterResult.Success -> {
                createAccount(
                    name = name,
                    email = email,
                    password = password
                )
            }
        }
    }
    private fun createAccount(
        name: String,
        email: String,
        password: String,
    )= viewModelScope.launch {
        registerUseCase.invoke(
            url = NetworkUrl.REGISTER_ENDPOINT,
            name = name,
            email = email,
            password = password,
        ).onStart {
            _registerUiState.update { state -> state.copy(isLoading = StatusLoading.SHOW_LOADING) }
        }.catch {
            _registerUiState.update { state ->
                state.copy(
                    isLoading = StatusLoading.DISMISS_LOADING,
                    errorDialog = setErrorDialog()
                )
            }
        }.collect { result ->
            when(result){
                is NetworkResult.Error -> {
                    _registerUiState.update { state ->
                        state.copy(
                            isLoading = StatusLoading.DISMISS_LOADING,
                            errorDialog = setErrorDialog()
                        )
                    }
                }
                is NetworkResult.Success -> {
                    result.response?. let { data ->
                        insertAndDeleteInfo(token = data.tokens, user = data.user)
                    } ?: run {
                        _registerUiState.update { state ->
                            state.copy(
                                isLoading = StatusLoading.DISMISS_LOADING,
                                errorDialog = setErrorDialog(),
                            )
                        }
                    }
                }
            }
        }
    }
    private suspend fun insertAndDeleteInfo(
        token: TokensModel,
        user: UserModel
    ){
        saveTokenUseCase(token = token)
            .catch {
                _registerUiState.update { state ->
                    state.copy(
                        isLoading = StatusLoading.DISMISS_LOADING,
                        errorDialog = setErrorDialog(),
                    )
                }
            }.collect {
                insertUserAndDelete(userModel = user)
            }
    }

    private suspend fun insertUserAndDelete(
        userModel: UserModel
    ){
        saveUserUseCase(user = userModel)
            .catch {
                _registerUiState.update { state ->
                    state.copy(
                        isLoading = StatusLoading.DISMISS_LOADING,
                        errorDialog = setErrorDialog(),
                    )
                }
            }.collect {
                _registerUiState.update { state ->
                    state.copy(
                        isLoading = StatusLoading.DISMISS_LOADING,
                    )
                }
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
}
