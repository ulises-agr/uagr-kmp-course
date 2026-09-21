/*
 * LoginNavigation.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.login.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.uagr.kmp.course.presentation.login.ui.LoginScreen

data object LoginNavigation : Screen {
    @Composable
    override fun Content() {
        LoginScreen()
    }
}