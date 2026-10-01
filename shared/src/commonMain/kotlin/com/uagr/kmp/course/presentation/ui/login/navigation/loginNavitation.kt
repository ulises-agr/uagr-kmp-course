/*
 * loginNavigation.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.login.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.uagr.kmp.course.presentation.ui.home.navigation.HomeNavigation
import com.uagr.kmp.course.presentation.ui.login.ui.loginScreen
import com.uagr.kmp.course.presentation.ui.register.navigation.RegisterNavigation

//import com.uagr.kmp.course.presentation.ui.welcome.navigation.HomeNavigation

data object loginNavigation : Screen  {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        loginScreen(
            onLoginSuccess = {
                navigator.replaceAll(HomeNavigation)
            },
            onRegisterClick = {
                navigator.push(RegisterNavigation)
            }
        )
    }
}