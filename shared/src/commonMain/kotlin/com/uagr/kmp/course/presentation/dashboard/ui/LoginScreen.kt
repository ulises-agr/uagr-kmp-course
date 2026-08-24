/*
 * DashboardScreen.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.dashboard.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.component.container.SafeScreenContainer
import com.uagr.kmp.course.component.container.SafeScreenContainerTest

@Composable
fun DashboardScreen(
    onNavigateToLogin: () -> Unit = {},
) {
    SafeScreenContainer {
        DashboardContainer(onNavigateToLogin = onNavigateToLogin)
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun DashboardScreenPreview() {
    SafeScreenContainerTest {
        DashboardScreen()
    }
}
