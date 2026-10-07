package com.uagr.kmp.course.presentation.ui.movements.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.uagr.kmp.course.presentation.ui.movements.ui.MovementsScreen

data object MovementsTab : Tab {

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 3u,
            title = "Movements",
            icon = null
        )
    @Composable
    override fun Content() {
        MovementsScreen()
    }
}