package com.uagr.kmp.course.presentation.ui.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.dialog.DialogCustom
import com.uagr.kmp.course.presentation.component.loader.Loader
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.ui.home.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
) {
    val homeUiState by viewModel.homeUiState.collectAsStateWithLifecycle()

    SafeScreenContainer {
        HomeContainer(
            name = homeUiState.name,
            totalBalance = homeUiState.totalBalance,
            incomeTotal = homeUiState.monthlyIncome,
            expensesTotal = homeUiState.monthlyExpenses,
            monthlySavings = homeUiState.monthlySavings,
            savingsRate = homeUiState.savingsRate,
            recentTransactions = homeUiState.recentTransactions
        )
        Loader()
        DialogCustom(
            errorDialog = homeUiState.errorDialog,
            titleTextColor = AppTheme.colors.text.blue,
            messageTextColor = AppTheme.colors.text.blue,
            primaryButtonTextColor = AppTheme.colors.text.white,
            primaryButtonBackgroundColor = AppTheme.colors.primary,
            onPrimaryButtonClick = {
                viewModel.dismissErrorDialog()
            }
        )
    }
}
@Preview(
    showBackground = true
)
@Composable
private fun HomeScreenPreview(){
    SafeScreenContainer {
        HomeScreen()
    }
}