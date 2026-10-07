package com.uagr.kmp.course.presentation.ui.budget.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.uagr.kmp.course.presentation.ui.budget.ui.BudgetScreen

data object BudgetTab : Tab {

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 1u,
            title = "Budget",
            icon = null
        )
    @Composable
    override fun Content() {
        BudgetScreen()
    }
}