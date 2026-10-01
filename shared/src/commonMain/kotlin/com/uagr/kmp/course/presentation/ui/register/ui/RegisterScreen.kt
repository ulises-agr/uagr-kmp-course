package com.uagr.kmp.course.presentation.ui.register.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.ui.login.ui.LoginContainer

/*
// RegisterScreen.kt
// Created by Karla Judith Cervantes Higareda on 29/09/26.
// Copyright (c) 2026. All rights reserved.
*/

@Composable
fun RegisterScreen(
    navigateToHome: () -> Unit = {},
    navigateToLogin: () -> Unit = {},
) {
    SafeScreenContainer {
        RegisterContainer(
            navigateToHome = navigateToHome,
            navigateToLogin = navigateToLogin,
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun RegisterScreenPreview() {
    SafeScreenContainerTest {
        RegisterScreen()
    }
}