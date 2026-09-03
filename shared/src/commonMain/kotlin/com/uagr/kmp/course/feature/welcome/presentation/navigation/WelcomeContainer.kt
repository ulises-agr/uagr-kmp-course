package com.uagr.kmp.course.feature.welcome.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uagr.kmp.course.core.ui.components.MainContainerScreen
import com.uagr.kmp.course.feature.welcome.presentation.WelcomeUiEffect
import com.uagr.kmp.course.feature.welcome.presentation.WelcomeViewModel

@Composable
fun WelcomeContainer(
    navigateToHome: () -> Unit = {}
) {
    MainContainerScreen {
        val viewModel: WelcomeViewModel = viewModel()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.uiEffect.collect { effect ->
                when (effect) {
                    is WelcomeUiEffect.NavigateToHome -> navigateToHome()
                    is WelcomeUiEffect.ShowToast -> { /* Mostrar Snackbar/Toast */ }
                    else -> {}
                }
            }
        }

        WelcomeScreen(
            state = state,
            onEvent = { event ->
                viewModel.onEvent(event)
            }
        )
    }
}