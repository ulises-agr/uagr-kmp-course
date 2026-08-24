/*
 * DashboardScreen.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.dashboard.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.dialog.ErrorDialog
import com.uagr.kmp.course.presentation.component.loader.Loader
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.ui.dashboard.viewmodel.DashboardUiEvent
import com.uagr.kmp.course.presentation.ui.dashboard.viewmodel.DashboardViewModel
import com.uagr.kmp.course.utils.animation.Animation
import course.shared.generated.resources.Res
import course.shared.generated.resources.accept
import course.shared.generated.resources.warning
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = koinViewModel(),
    onNavigateToLogin: () -> Unit = {},
) {
    val dashboardUiState by viewModel.dashboardUiState.collectAsStateWithLifecycle()
    val dashboardUiEvent by viewModel.dashboardUiEvent.collectAsStateWithLifecycle()

    SafeScreenContainer {
        AnimatedContent(
            targetState = dashboardUiState.isLoading,
            transitionSpec = { Animation.scaleTransition() },
        ) { currentState ->
            DashboardContainer(
                email = dashboardUiState.email,
                onNavigateToLogin = onNavigateToLogin,
            )

            Loader(
                backgroundColor = AppTheme.colors.background,
                currentState = currentState,
            )
        }
    }

    when (dashboardUiEvent) {
        is DashboardUiEvent.Idle -> {}
        is DashboardUiEvent.ShowErrorDialog -> {
            ErrorDialog(
                titleTextColor = Color.Black,
                titleText = stringResource(Res.string.warning),
                descriptionTextColor = Color.Black,
                descriptionText = stringResource((dashboardUiEvent as DashboardUiEvent.ShowErrorDialog).message),
                primaryButtonBackgroundColor = Color.Black,
                primaryButtonTextColor = Color.White,
                primaryButtonText = stringResource(Res.string.accept),
                onPrimaryButtonClick = {
                    viewModel.resetUiEvent()
                }
            )
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun DashboardScreenPreview() {
    SafeScreenContainerTest {
        DashboardContainer()
    }
}
