/*
 * LoginScreen.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.presentation.welcome.viewmodel.LoginUiEvent
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.dialog.DialogCustom
import com.uagr.kmp.course.presentation.component.loader.Loader
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.ui.login.viewmodel.LoginViewModel
import com.uagr.kmp.course.utils.flow.CollectWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {},
    navigateToRegister: () -> Unit = {},
    viewModel: LoginViewModel = koinViewModel(),
) {
    val loginUiState by viewModel.loginUiState.collectAsStateWithLifecycle()
    viewModel.loginUiEvent.CollectWithLifecycle { event ->
        when(event){
            is LoginUiEvent.Idle -> {}
            is LoginUiEvent.LoginSuccess -> {
                viewModel.resetUiEvent()
                onLoginSuccess()
            }
            is LoginUiEvent.ShowVersionInfoDialog -> {}
        }
    }
    SafeScreenContainer {
        LoginContainer(
            email = loginUiState.email,
            onEmailChange = { email ->
                viewModel.updateEmail(email = email)
            },
            password = loginUiState.password,
                onPasswordChange = {password ->
                    viewModel.updatePassword(password = password)
                },
            navigateToRegister = navigateToRegister,
            onLoginClick = {
                viewModel.validateLoginForm(
                    email = loginUiState.email,
                    password = loginUiState.password
                )
            },
        )
        Loader()
        DialogCustom(
            errorDialog = loginUiState.errorDialog,
            titleTextColor = AppTheme.colors.text.blue,
            messageTextColor = AppTheme.colors.text.blue,
            primaryButtonTextColor = AppTheme.colors.text.white,
            primaryButtonBackgroundColor = AppTheme.colors.primary,
            onPrimaryButtonClick = {
                viewModel.dismissErrorDialog()
            }
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_9,
)
@Composable
private fun WelcomeScreenPreview() {
    SafeScreenContainerTest {
        LoginScreen()
    }
}
