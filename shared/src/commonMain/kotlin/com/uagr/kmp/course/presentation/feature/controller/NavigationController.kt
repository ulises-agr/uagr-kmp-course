/*
 * NavigationController.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.feature.controller

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.ScaleTransition
import com.uagr.kmp.course.presentation.feature.packages.navigation.PackagesNavigation
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.feature.welcome.navigation.WelcomeNavigation

@Composable
fun NavigationController() {
    AppTheme {
        Navigator(screen = PackagesNavigation) { navigator ->
            ScaleTransition(navigator)
        }
    }
}
