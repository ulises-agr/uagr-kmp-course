/*
 * PackagesScreen.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.uagr.packages.sdk.presentation.PackagesUiEvent
import com.uagr.packages.sdk.presentation.PackagesViewModel
import com.uagr.packages.sdk.ui.component.PackagesSdkLoader
import com.uagr.packages.sdk.ui.component.PackagesSdkScreenScaffold
import com.uagr.packages.sdk.ui.theme.PackagesSdkTheme
import org.koin.compose.viewmodel.koinViewModel

/**
 * Public Compose entry point for the Packages feature.
 * Host apps only need to call this composable (navigation stays in the host).
 */
@Composable
fun PackagesScreen(
    viewModel: PackagesViewModel = koinViewModel(),
    onPackageClick: () -> Unit = {},
) {
    PackagesSdkTheme {
        val packagesUiState by viewModel.packagesUiState.collectAsState()
        val packagesUiEvent by viewModel.packagesUiEvent.collectAsState()

        Box(modifier = Modifier.fillMaxSize()) {
            PackagesSdkScreenScaffold {
                PackagesContainer(
                    packages = packagesUiState.packages,
                    onPackageClick = onPackageClick,
                )
            }
            PackagesSdkLoader(isLoading = packagesUiState.isLoading)
        }

        when (packagesUiEvent) {
            is PackagesUiEvent.Idle -> Unit
            is PackagesUiEvent.ShowError -> Unit
        }
    }
}
