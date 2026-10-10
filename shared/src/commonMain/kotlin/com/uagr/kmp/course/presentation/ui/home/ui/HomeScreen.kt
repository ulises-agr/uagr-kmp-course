/*
// HomeScreen.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.card.HomeMenuOption
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.domain.model.base.TransactionItemModel
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.mock.homeDataMock
import com.uagr.kmp.course.presentation.ui.home.viewmodel.HomeUiState

@Composable
fun HomeScreen(
    navigateToHome: () -> Unit = {},
    navigateToTransactions: () -> Unit = {},
    navigateToBudget: () -> Unit = {},
    navigateToGoals: () -> Unit = {},
    uiState: HomeUiState = homeDataMock,
    onOptionsClick: (() -> Unit)? = null,
    onTransactionClick: ((TransactionItemModel) -> Unit)? = null,
) {
    var selectedMenuName by rememberSaveable { mutableStateOf(HomeMenuOption.HOME.name) }

    SafeScreenContainer {
        HomeContainer(
            uiState = uiState,
            onViewAllClick = {
                selectedMenuName = HomeMenuOption.TRANSACTIONS.name
                navigateToTransactions()
            },
            onOptionsClick = onOptionsClick,
            onTransactionClick = onTransactionClick,
            selectedMenu = HomeMenuOption.valueOf(selectedMenuName),
            onMenuSelected = { option ->
                if (selectedMenuName != option.name) {
                    selectedMenuName = option.name
                    when (option) {
                        HomeMenuOption.HOME -> navigateToHome()
                        HomeMenuOption.TRANSACTIONS -> navigateToTransactions()
                        HomeMenuOption.BUDGET -> navigateToBudget()
                        HomeMenuOption.GOALS -> navigateToGoals()
                    }
                }
            },
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun HomeScreenPreview() {
    SafeScreenContainerTest {
        HomeContainer()
    }
}
