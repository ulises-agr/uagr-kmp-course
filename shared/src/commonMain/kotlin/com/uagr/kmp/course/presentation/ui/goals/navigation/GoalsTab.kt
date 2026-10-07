package com.uagr.kmp.course.presentation.ui.goals.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.uagr.kmp.course.presentation.ui.goals.ui.GoalsScreen

data object GoalsTab : Tab {

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 2u,
            title = "Goals",
            icon = null
        )
    @Composable
    override fun Content() {
        GoalsScreen()
    }
}