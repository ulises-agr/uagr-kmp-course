package com.uagr.kmp.course.presentation.ui.register.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.uagr.kmp.course.presentation.ui.home.navigation.HomeNavigation
import com.uagr.kmp.course.presentation.ui.register.ui.RegisterScreen
import com.uagr.kmp.course.presentation.ui.register.viewmodel.RegisterViewModel
import org.koin.compose.viewmodel.koinViewModel

data object RegisterNavigation : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val viewModel = koinViewModel<RegisterViewModel>()

        RegisterScreen(
            viewModel = viewModel,
            onBackClicked = {
                navigator.pop()
            },
            onRegisterSuccess = {
                navigator.replace(HomeNavigation)
            }
        )
    }
}