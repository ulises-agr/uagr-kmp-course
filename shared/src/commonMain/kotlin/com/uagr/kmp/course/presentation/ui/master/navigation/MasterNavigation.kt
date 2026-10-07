package com.uagr.kmp.course.presentation.ui.master.navigation


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.uagr.kmp.course.presentation.ui.home.navigation.HomeTab
import com.uagr.kmp.course.presentation.ui.master.ui.MasterScreen

data object MasterNavigation : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        TabNavigator(
            HomeTab
        ) {
            MasterScreen()
        }
    }
}

@Composable
fun MasterContent(
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        content()
    }
}
