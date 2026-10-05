/*
 * HomeScreen.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.theme.AppTheme

@Composable
fun HomeScreen(
    onLogoutClick: () -> Unit = {}
) {
    SafeScreenContainer(
        systemColor = AppTheme.colors.background,
        backgroundColor = AppTheme.colors.background,
        isSystemIconsDark = true,
    ) {
        HomeContainer(
            onLogoutClick = onLogoutClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SafeScreenContainerTest {
        HomeContainer()
    }
}