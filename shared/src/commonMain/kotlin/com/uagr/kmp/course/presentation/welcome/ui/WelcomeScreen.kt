/*
 * HeatScreen.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.welcome.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.component.container.SafeScreenContainer
import com.uagr.kmp.course.component.container.SafeScreenContainerTest

@Composable
fun WelcomeScreen(
    onNavigateToLogin: () -> Unit = {},
) {
    SafeScreenContainer {
        WelcomeContainer(
            onNavigateToLogin = onNavigateToLogin,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeScreenPreview() {
    SafeScreenContainerTest {
        WelcomeScreen()
    }
}
