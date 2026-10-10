/*
// RegisterScreen.kt
// Created by Karla Judith Cervantes Higareda on 29/09/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.register.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.dialog.DialogCustom
import com.uagr.kmp.course.presentation.component.loader.Loader
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.ui.register.viewmodel.RegisterUiEvent
import com.uagr.kmp.course.presentation.ui.register.viewmodel.RegisterViewModel
import com.uagr.kmp.course.utils.operators.StatusLoading
import course.shared.generated.resources.Res
import course.shared.generated.resources.error
import course.shared.generated.resources.register_accept
import course.shared.generated.resources.register_success_title
import course.shared.generated.resources.register_success_message
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = koinViewModel(),
    navigateToBack: () -> Unit = {},
    navigateToHome: () -> Unit = {},
) {
    val registerUiState by viewModel.registerUiState.collectAsStateWithLifecycle()
    val registerUiEvent by viewModel.registerUiEvent.collectAsStateWithLifecycle()

    SafeScreenContainer {
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
            passwordVisible = registerUiState.passwordVisible,
            onPasswordVisibleChange = { passwordVisible ->
                viewModel.updatePasswordVisible(passwordVisible = passwordVisible)
            },
            confirmPasswordVisible = registerUiState.confirmPasswordVisible,
            onConfirmPasswordVisibleChange = { confirmPasswordVisible ->
                viewModel.updateConfirmPasswordVisible(confirmPasswordVisible = confirmPasswordVisible)
            },
            onCreateRegister = {
                viewModel.validateForms(
                    name = registerUiState.name,
                    email = registerUiState.email,
                    password = registerUiState.password,
                    confirmPassword = registerUiState.confirmPassword,
                )
            },
            navigateToBack = navigateToBack,
            isLoading = registerUiState.isLoading == StatusLoading.SHOW_LOADING,
        )
        Loader(isLoading = registerUiState.isLoading)
    }

    val message = when (val currentEvent = registerUiEvent) {
        RegisterUiEvent.Idle -> null
        RegisterUiEvent.Registered -> stringResource(Res.string.register_success_message)
        is RegisterUiEvent.ShowErrorDialog -> stringResource(currentEvent.message)
        is RegisterUiEvent.ShowErrorStringDialog -> currentEvent.message
    }
    if (message != null) {
        DialogCustom(
            errorDialog = ErrorDialogModel(
                title = stringResource(
                    if (registerUiEvent == RegisterUiEvent.Registered) Res.string.register_success_title else Res.string.error
                ),
                message = message,
                primaryButtonText = stringResource(Res.string.register_accept),
            ),
            titleTextColor = AppTheme.colors.text.black,
            messageTextColor = AppTheme.colors.text.black,
            primaryButtonBackgroundColor = AppTheme.colors.backgrounds.blue,
            primaryButtonTextColor = AppTheme.colors.text.white,
            onPrimaryButtonClick = {
                val registered = registerUiEvent == RegisterUiEvent.Registered
                viewModel.clearEvent()
                if (registered) navigateToHome()
            },
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun RegisterScreenPreview() {
    SafeScreenContainerTest {
        RegisterContainer()
    }
}
