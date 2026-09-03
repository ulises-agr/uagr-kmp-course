package com.uagr.kmp.course.feature.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.core.ui.components.MainContainerScreen


@Composable
@Preview
fun HomeContainer(
    navigateToWelcome: () -> Unit = {},
) {
    MainContainerScreen {
        HomeScreen(
            navigateToWelcome = { navigateToWelcome() }
        )
    }
}