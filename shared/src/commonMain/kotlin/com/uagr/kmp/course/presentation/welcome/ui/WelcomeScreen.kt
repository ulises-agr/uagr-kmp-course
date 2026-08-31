/*
 * HeatScreen.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.welcome.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.LocalNavigator
import com.uagr.kmp.course.component.container.SafeScreenContainer
import com.uagr.kmp.course.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.card.navigation.CardNavigation
import com.uagr.kmp.course.theme.AppTheme

@Composable
fun WelcomeScreen() {
    SafeScreenContainer(
        backgroundColor = AppTheme.colors.backgrounds.backgroundColor
    ) {
        val navigator = LocalNavigator.current
        WelcomeContainer(
            onClickButton = {
                navigator?.push(CardNavigation(param = 0))
            }
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
