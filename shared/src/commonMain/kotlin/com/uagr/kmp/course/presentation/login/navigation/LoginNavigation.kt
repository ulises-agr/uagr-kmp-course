/*
 * LoginNavigation.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.login.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.uagr.kmp.course.presentation.login.ui.LoginScreen

data object LoginScreenInstance : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        LoginScreen(
            onNavigateToWelcome = {
                navigator.pop()
                // navigator.replaceAll(item = WelcomeScreenInstance)
            },
        )
    }
}
