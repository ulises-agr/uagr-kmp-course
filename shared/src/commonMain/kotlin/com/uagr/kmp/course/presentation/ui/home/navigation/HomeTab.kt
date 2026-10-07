package com.uagr.kmp.course.presentation.ui.home.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.uagr.kmp.course.presentation.ui.home.ui.HomeScreen

data object HomeTab : Tab {

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 0u,
            title = "Home",
            icon = null
        )
@Composable
    override fun Content() {
        HomeScreen()
    }
}