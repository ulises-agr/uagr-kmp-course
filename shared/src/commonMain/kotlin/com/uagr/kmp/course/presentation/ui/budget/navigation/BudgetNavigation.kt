package com.uagr.kmp.course.presentation.ui.budget.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.uagr.kmp.course.presentation.ui.budget.ui.BudgetScreen

data object BudgetNavigation : Screen {
    @Composable
    override fun Content() {
        BudgetScreen()
    }
}
