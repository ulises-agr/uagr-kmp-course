package com.uagr.kmp.course.presentation.ui.register.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.uagr.kmp.course.presentation.ui.register.viewmodel.RegisterUIEvent
import com.uagr.kmp.course.presentation.ui.register.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    onBackClicked: () -> Unit,
    onRegisterSuccess: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                RegisterUIEvent.RegisterSuccess -> {
                    onRegisterSuccess()
                }
            }
        }
    }

    RegisterContainer(
        name = uiState.name,
        email = uiState.email,
        password = uiState.password,
        confirmPassword = uiState.confirmPassword,
        nameError = uiState.nameError,
        emailError = uiState.emailError,
        passwordError = uiState.passwordError,
        confirmPasswordError = uiState.confirmPasswordError,
        registerError = uiState.registerError,
        isLoading = uiState.isLoading,
        onNameChanged = viewModel::onNameChanged,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onConfirmPasswordChanged = viewModel::onConfirmPasswordChanged,
        onRegisterClicked = viewModel::onRegisterClicked,
        onBackClicked = onBackClicked
    )
}