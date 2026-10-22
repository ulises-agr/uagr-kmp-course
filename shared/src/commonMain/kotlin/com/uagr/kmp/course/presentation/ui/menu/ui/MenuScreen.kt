/*
// MenuScreen.kt
// Created by Karla Judith Cervantes Higareda on 10/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.menu.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uagr.kmp.course.presentation.component.card.HomeMenuOption
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.ui.login.ui.LoginContainer
import com.uagr.kmp.course.presentation.ui.menu.viewmodel.MenuUiEvent
import com.uagr.kmp.course.presentation.ui.menu.viewmodel.MenuUiState
import com.uagr.kmp.course.presentation.ui.menu.viewmodel.MenuViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MenuScreen(
    activeOption: HomeMenuOption,
    viewModel: MenuViewModel = koinViewModel(),
    navigateToHome: () -> Unit = {},
    navigateToTransactions: () -> Unit = {},
    navigateToBudget: () -> Unit = {},
    navigateToGoals: () -> Unit = {},
    content: @Composable (onMenuSelected: (HomeMenuOption) -> Unit) -> Unit,
) {
    val menuUiState by viewModel.menuUiState.collectAsStateWithLifecycle()
    val menuUiEvent by viewModel.menuUiEvent.collectAsStateWithLifecycle()

    LaunchedEffect(activeOption, viewModel) {
        viewModel.updateActiveOption(activeOption)
    }

    LaunchedEffect(menuUiEvent, viewModel) {
        val event = menuUiEvent
        if (event is MenuUiEvent.Navigate) {
            viewModel.clearEvent()
            when (event.option) {
                HomeMenuOption.HOME -> navigateToHome()
                HomeMenuOption.TRANSACTIONS -> navigateToTransactions()
                HomeMenuOption.BUDGET -> navigateToBudget()
                HomeMenuOption.GOALS -> navigateToGoals()
            }
        }
    }

    MenuContainer(
        uiState = menuUiState,
        onMenuSelected = viewModel::selectOption,
    ) {
        content(viewModel::selectOption)
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun MenuScreenPreview() {
    SafeScreenContainerTest {
        MenuContainer(uiState = MenuUiState(), onMenuSelected = {}) {

        }
    }
}
