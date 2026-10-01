package com.uagr.kmp.course.presentation.ui.home.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.uagr.kmp.course.presentation.ui.home.ui.HomeScreen

data object HomeNavigation : Screen {
    @Composable
    override fun Content() {
        HomeScreen()
    }
}