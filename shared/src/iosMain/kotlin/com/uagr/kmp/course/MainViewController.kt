/*
 * MainViewController.kt
 * Copyright (c) 2026. All rights reserved
*/
package com.uagr.kmp.course

import androidx.compose.ui.window.ComposeUIViewController
import com.uagr.kmp.course.di.initKoin
import com.uagr.kmp.course.presentation.ui.controller.NavigationController
import com.uagr.kmp.course.util.logs.KmpLogger
import io.github.aakira.napier.DebugAntilog

fun MainViewController() = ComposeUIViewController(
    configure = {
        // Logs KMP
        KmpLogger.init(antilog = DebugAntilog())
        // DI KMP
        initKoin()
    },
) {
    NavigationController()
}
