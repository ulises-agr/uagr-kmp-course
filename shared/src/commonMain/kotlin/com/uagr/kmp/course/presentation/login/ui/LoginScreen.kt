/*
 * LoginScreen.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.component.container.SafeScreenContainer
import com.uagr.kmp.course.component.container.SafeScreenContainerTest

@Composable
fun LoginScreen(
    onNavigateToWelcome: () -> Unit = {},
) {
    SafeScreenContainer {
        LoginContainer(
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
