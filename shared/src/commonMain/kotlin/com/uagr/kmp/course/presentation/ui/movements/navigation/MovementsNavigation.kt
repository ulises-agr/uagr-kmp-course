package com.uagr.kmp.course.presentation.ui.movements.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.uagr.kmp.course.presentation.ui.movements.ui.MovementsScreen

data object MovementsNavigation : Screen {
    @Composable
    override fun Content() {
        MovementsScreen()
    }
}
