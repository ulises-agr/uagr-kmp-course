/*
 * PackagesNavigation.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.feature.packages.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.uagr.packages.sdk.PackagesSdk

/**
 * Host navigation entry — only invokes the Packages SDK UI.
 */
data object PackagesNavigation : Screen {
    @Composable
    override fun Content() {
        PackagesSdk.Content()
    }
}
