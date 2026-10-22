/*
// LoginScreen.kt
// Created by Karla Judith Cervantes Higareda on 29/09/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.loader.Loader
import com.uagr.kmp.course.presentation.component.dialog.DialogCustom
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.ui.login.viewmodel.LoginUiEvent
import com.uagr.kmp.course.presentation.ui.login.viewmodel.LoginViewModel
import com.uagr.kmp.course.utils.operators.StatusLoading
import org.koin.compose.viewmodel.koinViewModel
import course.shared.generated.resources.Res
import course.shared.generated.resources.error
import course.shared.generated.resources.login_accept
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    navigateToRegister: () -> Unit = {},
    navigateToHome: () -> Unit = {},
) {
    val loginUiState by viewModel.loginUiState.collectAsStateWithLifecycle()
    val loginUiEvent by viewModel.loginUiEvent.collectAsStateWithLifecycle()

    LaunchedEffect(loginUiEvent) {
        if (loginUiEvent == LoginUiEvent.LoginSuccess) {
            viewModel.clearEvent()
            navigateToHome()
        }
    }

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
            onLoginClick = {
                viewModel.validateForms(
                    email = loginUiState.email,
                    password = loginUiState.password,
                )
            },
            navigateToRegister = navigateToRegister,
            isLoading = loginUiState.isLoading == StatusLoading.SHOW_LOADING,
        )
        Loader(isLoading = loginUiState.isLoading)
    }

    val message = when (val event = loginUiEvent) {
        LoginUiEvent.Idle -> null
        LoginUiEvent.LoginSuccess -> null
        is LoginUiEvent.ShowErrorDialog -> stringResource(event.message)
        is LoginUiEvent.ShowErrorStringDialog -> event.message
    }
    if (message != null) {
        DialogCustom(
            errorDialog = ErrorDialogModel(
                title = stringResource(Res.string.error),
                message = message,
                primaryButtonText = stringResource(Res.string.login_accept),
            ),
            titleTextColor = AppTheme.colors.text.black,
            messageTextColor = AppTheme.colors.text.black,
            primaryButtonBackgroundColor = AppTheme.colors.backgrounds.blue,
            primaryButtonTextColor = AppTheme.colors.text.white,
            onPrimaryButtonClick = {
                viewModel.clearEvent()
            },
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun LoginScreenPreview() {
    SafeScreenContainerTest {
        LoginContainer()
    }
}
