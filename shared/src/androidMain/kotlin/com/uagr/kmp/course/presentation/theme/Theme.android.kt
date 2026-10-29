/*
 * Theme.android.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.theme

import android.app.Activity
import android.os.Build
import android.view.WindowInsets
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat
import com.uagr.kmp.course.presentation.theme.AppTheme

@Composable
internal actual fun SystemAppearance(isDarkMode: Boolean) {
    val view = LocalView.current
    val statusBarColor = AppTheme.colors.primary.toArgb()
    val navigationBarColor = AppTheme.colors.primary.toArgb()
    LaunchedEffect(key1 = isDarkMode) {
        val window = (view.context as Activity).window
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = isDarkMode
            isAppearanceLightNavigationBars = isDarkMode
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) { // Android 15+
            window.decorView.setOnApplyWindowInsetsListener { view, insets ->
                val statusBarInsets = insets.getInsets(WindowInsets.Type.statusBars())
                view.setBackgroundColor(navigationBarColor)
                // Adjust padding to avoid overlap
                view.setPadding(0, statusBarInsets.top, 0, 0)
                insets
            }
        } else {
            window.statusBarColor = statusBarColor
            window.navigationBarColor = navigationBarColor
        }
    }
}
