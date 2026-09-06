/*
 * PackagesScreen.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.packages.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.loader.Loader
import com.uagr.kmp.course.presentation.ui.packages.viewmodel.PackagesUiEvent
import com.uagr.kmp.course.presentation.ui.packages.viewmodel.PackagesViewModel
import com.uagr.kmp.course.utils.animation.Animation
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PackagesScreen(
    viewmodel: PackagesViewModel = koinViewModel(),
) {
    val packagesUiState by viewmodel.packagesUiState.collectAsState()
    val packagesUiEvent by viewmodel.packagesUiEvent.collectAsState()

    SafeScreenContainer {
        AnimatedContent(
            targetState = packagesUiState.isLoading,
            transitionSpec = { Animation.scaleTransition() },
        ) { currentState ->
            PackagesContainer(
                activePackagesClick = {},
            )
            Loader(currentState = currentState)
        }
    }

    when (packagesUiEvent) {
        is PackagesUiEvent.Idle -> {}
        is PackagesUiEvent.ShowErrorDialog -> {}
    }
}

@Preview(showBackground = true)
@Composable
private fun PackagesScreenPreview() {
    SafeScreenContainerTest {
        PackagesScreen()
    }
}
