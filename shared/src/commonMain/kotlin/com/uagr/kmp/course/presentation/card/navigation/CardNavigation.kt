package com.uagr.kmp.course.presentation.card.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.uagr.kmp.course.presentation.card.ui.CardScreen

data class CardNavigation(val param : Int) : Screen {
    @Composable
    override fun Content() {
        CardScreen()
    }
}