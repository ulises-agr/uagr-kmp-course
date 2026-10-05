package com.uagr.kmp.course.presentation.ui.goals.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.uagr.kmp.course.presentation.ui.goals.ui.GoalsScreen

data object GoalsNavigation : Screen {
    @Composable
    override fun Content() {
        GoalsScreen()
    }
}
