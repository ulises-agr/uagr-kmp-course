/*
 * PackagesScreen.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.packages.ui.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.loader.Loader
import com.uagr.kmp.course.presentation.ui.packages.ui.viewmodel.PackagesUiEvent
import com.uagr.kmp.course.presentation.ui.packages.ui.viewmodel.PackagesViewModel
import com.uagr.kmp.course.utils.operators.StatusLoading
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PackagesScreen(
    viewmodel: PackagesViewModel = koinViewModel(),
) {

    val packageUiState by viewmodel.packagesUiState.collectAsState()
    val packagesUiEvent by viewmodel.packagesUiEvent.collectAsState()

    SafeScreenContainer {
        PackagesContainer(
            activePackagesClick = {
                viewmodel.showDialog()
            },
        )
        Loader(currentState = packageUiState.isLoading)
    }

    when(packagesUiEvent){
        is PackagesUiEvent.Idle -> {}
        is PackagesUiEvent.ShowDialog ->{
            Loader(currentState = StatusLoading.SHOW_LOADING)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PackagesScreenPreview() {
    SafeScreenContainerTest {
        PackagesScreen()
    }
}
