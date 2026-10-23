package com.uagr.kmp.course.presentation.ui.mainContainer.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.uagr.kmp.course.presentation.ui.mainContainer.ui.MainContainer

data object MainNavigation: Screen {
    @Composable
    override fun Content(){
        MainContainer()
    }
}