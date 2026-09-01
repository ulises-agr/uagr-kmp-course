/*
 * WelcomeNavigation.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.welcome.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.uagr.kmp.course.presentation.ui.welcome.ui.WelcomeScreen

data object WelcomeScreenInstance : Screen {
    @Composable
    override fun Content() {
        WelcomeScreen()
    }
}
