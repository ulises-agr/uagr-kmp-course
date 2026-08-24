/*
 * LoginScreenInstance.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.login.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.uagr.kmp.course.presentation.ui.dashboard.navigation.DashboardScreenInstance
import com.uagr.kmp.course.presentation.ui.login.ui.LoginScreen

data object LoginScreenInstance : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        LoginScreen(
            onLoginSuccess = {
                navigator.replaceAll(item = DashboardScreenInstance)
            },
            onNavigateToWelcome = {
                navigator.pop()
            },
        )
    }
}
