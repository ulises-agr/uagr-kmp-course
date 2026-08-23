/*
 * WelcomeNavigation.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.welcome.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.uagr.kmp.course.presentation.login.navigation.LoginScreenInstance
import com.uagr.kmp.course.presentation.welcome.ui.WelcomeScreen

data object WelcomeScreenInstance : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        WelcomeScreen(
            onNavigateToLogin = {
                navigator.push(item = LoginScreenInstance)
            },
        )
    }
}
