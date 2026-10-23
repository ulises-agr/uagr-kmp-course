package com.uagr.kmp.course.presentation.ui.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.uagr.kmp.course.presentation.ui.home.ui.HomeScreen
import course.shared.generated.resources.Res
import course.shared.generated.resources.home_tab
import course.shared.generated.resources.icon_circle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

data object HomeTab: Tab {
    override val options: TabOptions
    @Composable
    get(){
        val title = stringResource(Res.string.home_tab)
        val icon = painterResource(Res.drawable.icon_circle)
        return remember(title, icon){
            TabOptions(
                index = 0u,
                title = title,
                icon = icon
            )
        }
    }

    @Composable
    override fun Content(){
        HomeScreen()
    }
}