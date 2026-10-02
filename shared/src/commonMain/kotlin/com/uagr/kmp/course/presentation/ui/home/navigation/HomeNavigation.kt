/*
 * HomeNavigation.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.home.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.uagr.kmp.course.presentation.ui.home.ui.HomeScreen
import com.uagr.kmp.course.presentation.ui.login.navigation.loginNavigation

data object HomeNavigation : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        HomeScreen(
            onLogoutClick = {
                navigator.replaceAll(loginNavigation)
            }
        )
    }
}