package com.uagr.kmp.course.presentation.ui.register.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.uagr.kmp.course.presentation.ui.register.viewmodel.RegisterViewModel
import androidx.compose.runtime.LaunchedEffect
import com.uagr.kmp.course.presentation.ui.register.viewmodel.RegisterUIEvent

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    onBackClicked: () -> Unit,
    onRegisterSuccess: () -> Unit
) {

    val name by viewModel.name.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()

    val nameError by viewModel.nameError.collectAsState()
    val emailError by viewModel.emailError.collectAsState()
    val passwordError by viewModel.passwordError.collectAsState()
    val confirmPasswordError by viewModel.confirmPasswordError.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->

            when (event) {
                RegisterUIEvent.RegisterSuccess -> {
                    onRegisterSuccess()
                }

                is RegisterUIEvent.RegisterError -> {
                    println("Register error -> ${event.message}")
                }
            }
        }
    }

    RegisterContainer(
        name = name,
        email = email,
        password = password,
        confirmPassword = confirmPassword,
        nameError = nameError,
        emailError = emailError,
        passwordError = passwordError,
        confirmPasswordError = confirmPasswordError,
        isLoading = isLoading,
        onNameChanged = viewModel::onNameChanged,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onConfirmPasswordChanged = viewModel::onConfirmPasswordChanged,
        onRegisterClicked = viewModel::onRegisterClicked,
        onBackClicked = onBackClicked
    )
}