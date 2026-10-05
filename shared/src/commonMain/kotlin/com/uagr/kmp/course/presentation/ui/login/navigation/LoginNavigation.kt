package com.uagr.kmp.course.presentation.ui.login.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.uagr.kmp.course.presentation.ui.home.navigation.HomeNavigation
import com.uagr.kmp.course.presentation.ui.login.ui.LoginScreen
import com.uagr.kmp.course.presentation.ui.master.navigation.MasterNavigation
import com.uagr.kmp.course.presentation.ui.register.navigation.RegisterNavigation
import com.uagr.kmp.course.presentation.ui.welcome.navigation.WelcomeNavigation

data object LoginNavigation : Screen  {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        LoginScreen(
            onLoginSuccess = {
                navigator.replaceAll(item = MasterNavigation)
            },
            onRegisterEnter = {
                navigator.push(item = RegisterNavigation)
            },
        )
    }
}
