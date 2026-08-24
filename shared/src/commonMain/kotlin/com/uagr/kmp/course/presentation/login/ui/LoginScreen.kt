/*
 * LoginScreen.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.component.container.SafeScreenContainer
import com.uagr.kmp.course.component.container.SafeScreenContainerTest

@Composable
fun LoginScreen(
    onNavigateToDashboard: () -> Unit = {},
    onNavigateToWelcome: () -> Unit = {},
) {
    var email by remember { mutableStateOf(value = "") }
    var password by remember { mutableStateOf(value = "") }

    SafeScreenContainer {
        LoginContainer(
            email = email,
            password = password,
            onEmailChange = { newEmail -> email = newEmail },
            onPasswordChange = { newPassword -> password = newPassword },
            onNavigateToDashboard = onNavigateToDashboard,
            onNavigateToWelcome = onNavigateToWelcome,
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
