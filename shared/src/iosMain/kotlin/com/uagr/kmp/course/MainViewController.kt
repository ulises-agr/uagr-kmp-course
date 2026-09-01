/*
 * MainViewController.kt
 * Copyright (c) 2026. All rights reserved
*/
package com.uagr.kmp.course

import androidx.compose.ui.window.ComposeUIViewController
import com.uagr.kmp.course.di.initKoin
import com.uagr.kmp.course.presentation.ui.controller.NavigationController

fun MainViewController() = ComposeUIViewController(
    configure = {
        // DI KMP
        initKoin()
    },
) {
    NavigationController()
}
