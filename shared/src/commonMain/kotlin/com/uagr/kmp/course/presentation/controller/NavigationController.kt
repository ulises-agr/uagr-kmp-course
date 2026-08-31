/*
 * NavigationController.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.controller

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.uagr.kmp.course.presentation.welcome.navigation.WelcomeScreenInstance
import com.uagr.kmp.course.theme.AppTheme

@Composable
fun NavigationController() {
    AppTheme {
        Navigator(screen = WelcomeScreenInstance) { navigator ->
            SlideTransition(navigator)
        }
    }
}
