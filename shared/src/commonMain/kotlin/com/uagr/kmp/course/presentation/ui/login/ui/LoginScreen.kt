package com.uagr.kmp.course.presentation.ui.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.ui.login.viewmodel.LoginViewModel
import org.koin.compose.viewmodel.koinViewModel

/*
// LoginScreen.kt
// Created by Karla Judith Cervantes Higareda on 29/09/26.
// Copyright (c) 2026. All rights reserved.
*/

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    navigateToHome: () -> Unit = {},
    navigateToRegister: () -> Unit = {},
) {
    val loginUiState by viewModel.loginUiState.collectAsStateWithLifecycle()

    SafeScreenContainer {
        LoginContainer(
            email = loginUiState.email,
            password = loginUiState.password,
            passwordVisible = loginUiState.passwordVisible,
            onEmailChange = { email ->
                viewModel.updateEmail(email = email)
            },
            onPasswordChange = { password ->
                viewModel.updatePassword(password = password)
            },
            onPasswordVisibleChange = { passwordVisible ->
                viewModel.updatePasswordVisible(passwordVisible = passwordVisible)
            },
            navigateToHome = navigateToHome,
            navigateToRegister = navigateToRegister,
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun LoginScreenPreview() {
    SafeScreenContainerTest {
        LoginScreen()
    }
}