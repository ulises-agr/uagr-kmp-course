package com.uagr.kmp.course.presentation.ui.register.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.uagr.kmp.course.presentation.ui.login.navigation.LoginNavigation
import com.uagr.kmp.course.presentation.ui.register.ui.RegisterScreen

/*
// RegisterNavigation.kt
// Created by Karla Judith Cervantes Higareda on 29/09/26.
// Copyright (c) 2026. All rights reserved.
*/

data object RegisterNavigation : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        RegisterScreen(
            navigateToLogin = {
                navigator.push(item = LoginNavigation)
            },
            navigateToBack = {
                navigator.pop()
            },
        )
    }
}