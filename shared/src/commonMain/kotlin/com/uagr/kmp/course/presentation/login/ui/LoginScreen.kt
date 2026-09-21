/*
 * LoginScreen.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uagr.kmp.course.presentation.component.Loader.Loader
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.login.viewmodel.LoginUIEvent
import com.uagr.kmp.course.presentation.login.viewmodel.LoginViewModel
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.utils.flow.CollectWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel()
) {
    
   val loginUiState by viewModel.loginUiState.collectAsStateWithLifecycle()
    
    /** Launch Effect **/
    viewModel.loginUiEvent.CollectWithLifecycle{ event ->
        when(event){
            is LoginUIEvent.Idle -> {
            
            }
            is LoginUIEvent.LoginSuccess -> {
            
            }
            else -> {}
        }
    }
    
    SafeScreenContainer(
        backgroundColor = AppTheme.colors.backgrounds.backgroundColor
    ) {
        LoginContainer(
            onClickLogin = {
                viewModel.validateLoginForm(
                    email = loginUiState.email,
                    password = loginUiState.password
                )
            },
            email = loginUiState.email,
            emailError = loginUiState.emailError,
            onEmailChanged = { email ->
                viewModel.updateEmail(email = email)
            },
            password = loginUiState.password,
            passwordError = loginUiState.passwordError,
            onPasswordChanged = { password ->
                viewModel.updatePassword(password = password)
            },
            passwordVisible = loginUiState.passwordVisible,
            onPasswordVisibilityChanged = { isVisible ->
                viewModel.onPasswordVisibilityChanged(isVisible = isVisible)
            }
        )
        Loader(currentState = loginUiState.isLoading)
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    SafeScreenContainerTest {
        LoginScreen()
    }
}