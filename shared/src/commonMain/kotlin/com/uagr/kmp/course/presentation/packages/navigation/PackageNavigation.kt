package com.uagr.kmp.course.presentation.packages.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.uagr.kmp.course.presentation.packages.ui.PackageScreen

data class PackageNavigation(val param : Int) : Screen {
    @Composable
    override fun Content() {
        PackageScreen()
    }
}