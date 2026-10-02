package com.uagr.kmp.course.presentation.ui.login.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.uagr.kmp.course.presentation.ui.login.ui.LoginScreen
import com.uagr.kmp.course.presentation.ui.register.navigation.RegisterNavigation

/*
// LoginNavigation.kt
// Created by Karla Judith Cervantes Higareda on 29/09/26.
// Copyright (c) 2026. All rights reserved.
*/

data object LoginNavigation : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        LoginScreen(
            navigateToHome = {
                //navigator.push(item = HomeNavigation)
            },
        ) {
            navigator.push(item = RegisterNavigation)
        }
    }
}