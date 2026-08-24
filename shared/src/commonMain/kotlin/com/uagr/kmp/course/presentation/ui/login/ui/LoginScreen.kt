/*
 * DashboardScreen.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.login.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.dialog.ErrorDialog
import com.uagr.kmp.course.presentation.component.loader.Loader
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.ui.login.viewmodel.LoginUiEvent
import com.uagr.kmp.course.presentation.ui.login.viewmodel.LoginViewModel
import com.uagr.kmp.course.utils.animation.Animation
import course.shared.generated.resources.Res
import course.shared.generated.resources.accept
import course.shared.generated.resources.warning
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit = {},
    onNavigateToWelcome: () -> Unit = {},
) {
    val loginUiState by viewModel.loginUiState.collectAsStateWithLifecycle()
    val loginUiEvent by viewModel.loginUiEvent.collectAsStateWithLifecycle()

    SafeScreenContainer {
        AnimatedContent(
            targetState = loginUiState.isLoading,
            transitionSpec = { Animation.scaleTransition() },
        ) { currentState ->
            LoginContainer(
                email = loginUiState.email,
                password = loginUiState.password,
                onEmailChange = { email ->
                    viewModel.updateEmail(email = email)
                },
                onPasswordChange = { password ->
                    viewModel.updatePassword(password = password)
                },
                onNavigateToDashboard = {
                    viewModel.loginValidation(
                        email = loginUiState.email,
                        password = loginUiState.password,
                    )
                },
                onNavigateToWelcome = onNavigateToWelcome,
            )

            Loader(
                backgroundColor = AppTheme.colors.background,
                currentState = currentState,
            )
        }
    }

    when (loginUiEvent) {
        is LoginUiEvent.Idle -> {}
        is LoginUiEvent.ShowErrorDialog -> {
            ErrorDialog(
                titleTextColor = Color.Black,
                titleText = stringResource(Res.string.warning),
                descriptionTextColor = Color.Black,
                descriptionText = stringResource((loginUiEvent as LoginUiEvent.ShowErrorDialog).message),
                primaryButtonBackgroundColor = Color.Black,
                primaryButtonTextColor = Color.White,
                primaryButtonText = stringResource(Res.string.accept),
                onPrimaryButtonClick = {
                    viewModel.resetUiEvent()
                }
            )
        }
        is LoginUiEvent.LoginSuccess -> {
            viewModel.resetUiEvent()
            onLoginSuccess()
        }
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
