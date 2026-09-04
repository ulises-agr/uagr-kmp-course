package com.uagr.kmp.course.feature.home.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.core.ui.components.MainContainerScreen
import com.uagr.kmp.course.core.ui.theme.AppTheme


@Composable
fun HomeContainer(
    navigateToWelcome: () -> Unit = {},
) {
    MainContainerScreen {
        HomeScreen(
            activatePackage = {

            },
            navigateToWelcome = {
                navigateToWelcome()
            }
        )
    }
}

@Composable
@Preview(showSystemUi = true)
private fun HomeContainerPreview() {
    AppTheme {
        HomeScreen()
    }
}