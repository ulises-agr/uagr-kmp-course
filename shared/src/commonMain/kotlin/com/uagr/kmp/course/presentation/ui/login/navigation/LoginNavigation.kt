package com.uagr.kmp.course.presentation.ui.login.navigation

import androidx.compose.runtime.Composable
import org.koin.compose.viewmodel.koinViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.uagr.kmp.course.presentation.ui.home.navigation.HomeNavigation
import com.uagr.kmp.course.presentation.ui.login.ui.LoginScreen
import com.uagr.kmp.course.presentation.ui.login.viewmodel.LoginViewModel

data object LoginNavigation : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val viewModel = koinViewModel<LoginViewModel>()

        LoginScreen(
            viewModel = viewModel,
            navigateToHome = {
                navigator.push(HomeNavigation)
            }
        )
    }
}