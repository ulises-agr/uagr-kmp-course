
/*
 * NavigationController.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.controller

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.ScaleTransition
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.ui.home.navigation.HomeNavigation
import com.uagr.kmp.course.presentation.ui.welcome.navigation.WelcomeNavigation

@Composable
fun NavigationController() {
    AppTheme {
        // TODO: Cambiar a false
        val previewHome = true

        val initialScreen = if (previewHome) {
            HomeNavigation
        } else {
            WelcomeNavigation
        }

        Navigator(screen = initialScreen) { navigator ->
            ScaleTransition(navigator)
        }
    }
}
