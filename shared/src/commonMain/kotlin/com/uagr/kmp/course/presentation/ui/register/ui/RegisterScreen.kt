/*
 * RegisterScreen.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.register.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.dialog.DialogCustom
import com.uagr.kmp.course.presentation.component.loader.Loader
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.ui.register.viewModel.RegisterUiEvent
import com.uagr.kmp.course.presentation.ui.register.viewModel.RegisterViewModel
import com.uagr.kmp.course.utils.flow.CollectWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = koinViewModel(),
    onRegisterSuccess: () -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    val registerUiState by viewModel.registerUiState.collectAsStateWithLifecycle()

    viewModel.registerUiEvent.CollectWithLifecycle { event ->
        when (event) {
            is RegisterUiEvent.Idle -> {}
            is RegisterUiEvent.RegisterSuccess -> {
                viewModel.resetUiEvent()
                onRegisterSuccess()
            }
        }
    }

    SafeScreenContainer(
        systemColor = AppTheme.colors.background,
        backgroundColor = AppTheme.colors.background,
        isSystemIconsDark = true,
    ) {
        RegisterContainer(
            name = registerUiState.name,
            onNameChange = { name ->
                viewModel.updateName(name = name)
            },
            email = registerUiState.email,
            onEmailChange = { email ->
                viewModel.updateEmail(email = email)
            },
            password = registerUiState.password,
            onPasswordChange = { password ->
                viewModel.updatePassword(password = password)
            },
            confirmPassword = registerUiState.confirmPassword,
            onConfirmPasswordChange = { confirmPassword ->
                viewModel.updateConfirmPassword(confirmPassword = confirmPassword)
            },
            onRegisterClick = {
                viewModel.validateRegisterForm(
                    name = registerUiState.name,
                    email = registerUiState.email,
                    password = registerUiState.password,
                    confirmPassword = registerUiState.confirmPassword,
                )
            },
            onBackClick = onBackClick,
        )
        Loader(isLoading = registerUiState.isLoading)
        DialogCustom(
            errorDialog = registerUiState.errorDialog,
            titleTextColor = AppTheme.colors.text.black,
            messageTextColor = AppTheme.colors.text.black,
            primaryButtonBackgroundColor = AppTheme.colors.primary,
            primaryButtonTextColor = AppTheme.colors.text.white,
            onPrimaryButtonClick = {
                viewModel.dismissErrorDialog()
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    SafeScreenContainerTest {
        RegisterContainer()
    }
}