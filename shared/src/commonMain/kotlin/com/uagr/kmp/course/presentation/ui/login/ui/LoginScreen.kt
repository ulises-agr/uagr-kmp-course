package com.uagr.kmp.course.presentation.ui.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.uagr.kmp.course.presentation.ui.login.viewmodel.LoginUIEvent
import com.uagr.kmp.course.presentation.ui.login.viewmodel.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel, navigateToHome: () -> Unit) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val emailError by viewModel.emailError.collectAsState()
    val passwordError by viewModel.passwordError.collectAsState()
    val loginError by viewModel.loginError.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->

            when (event) {
                LoginUIEvent.LoginSuccess -> {
                    navigateToHome()
                }

                LoginUIEvent.InvalidCredentials -> {
                    println("LOGIN -> Credenciales incorrectas")
                }
            }
        }
    }

    LoginContainer(
        email = email,
        emailError = emailError,
        password = password,
        passwordError = passwordError,
        loginError = loginError,
        isLoading = isLoading,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onLoginClicked = viewModel::onLoginClicked
    )
}