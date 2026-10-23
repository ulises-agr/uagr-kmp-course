package com.uagr.kmp.course.presentation.ui.buget.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.dialog.DialogCustom
import com.uagr.kmp.course.presentation.component.loader.Loader
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.ui.buget.viewModel.BudgetViewModel
import com.uagr.kmp.course.presentation.ui.home.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BudgetScreen(
    //viewModel: BudgetViewModel = koinViewModel(),
) {
//val budgetUiState by viewModel.budgetUiState.collectAsStateWithLifecycle()
/*
    SafeScreenContainer {
        BudgetContainer(
            month = "October",
            data = budgetUiState.data
        )
        Loader()
        DialogCustom(
            errorDialog = budgetUiState.errorDialog,
            titleTextColor = AppTheme.colors.text.blue,
            messageTextColor = AppTheme.colors.text.blue,
            primaryButtonTextColor = AppTheme.colors.text.white,
            primaryButtonBackgroundColor = AppTheme.colors.primary,
            onPrimaryButtonClick = {
                viewModel.dismissErrorDialog()
            }
        )
    }*/
    BudgetContainer(
        month = "October",
    )
}
@Preview(
    showBackground = true
)
@Composable
private fun HomeScreenPreview(){
    SafeScreenContainer {
        BudgetScreen()
    }
}