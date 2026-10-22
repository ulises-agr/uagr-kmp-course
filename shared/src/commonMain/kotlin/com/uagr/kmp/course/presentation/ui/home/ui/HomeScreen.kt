/*
 * HomeScreen.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.dialog.DialogCustom
import com.uagr.kmp.course.presentation.component.loader.Loader
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.ui.home.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onLogoutClick: () -> Unit = {}
) {
    val homeUiState by viewModel.homeUiState.collectAsState()

    SafeScreenContainer(
        systemColor = AppTheme.colors.background,
        backgroundColor = AppTheme.colors.background,
        isSystemIconsDark = true,
    ) {
        val dashboard = homeUiState.dashboard

        HomeContainer(
            userName = homeUiState.userName,
            totalBalance = dashboard?.totalBalance ?: "$0.00",
            incomes = dashboard?.monthlyIncome ?: "+$0.00",
            expenses = dashboard?.monthlyExpenses ?: "-$0.00",
            monthlyExpenses = dashboard?.monthlyExpenses ?: "$0.00",
            monthlySavings = dashboard?.monthlySavings ?: "$0.00",
            onLogoutClick = onLogoutClick
        )
        Loader(isLoading = homeUiState.isLoading)
        DialogCustom(
            errorDialog = homeUiState.errorDialog,
            titleTextColor = AppTheme.colors.text.black,
            messageTextColor = AppTheme.colors.text.black,
            primaryButtonBackgroundColor = AppTheme.colors.primary,
            primaryButtonTextColor = AppTheme.colors.text.white,
            onPrimaryButtonClick = {
                viewModel.dismissErrorDialog()
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SafeScreenContainerTest {
        HomeContainer()
    }
}