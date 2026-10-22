/*
// HomeScreen.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uagr.kmp.course.presentation.component.card.HomeMenuOption
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.domain.model.base.TransactionItemModel
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.mock.homeDataMock
import com.uagr.kmp.course.presentation.ui.home.viewmodel.HomeViewModel
import com.uagr.kmp.course.presentation.ui.home.viewmodel.HomeUiEvent
import com.uagr.kmp.course.presentation.ui.menu.ui.MenuScreen
import com.uagr.kmp.course.presentation.ui.menu.ui.MenuContainer
import com.uagr.kmp.course.presentation.ui.menu.viewmodel.MenuUiState
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.presentation.component.dialog.DialogCustom
import com.uagr.kmp.course.presentation.component.loader.Loader
import com.uagr.kmp.course.presentation.theme.AppTheme
import course.shared.generated.resources.Res
import course.shared.generated.resources.error
import course.shared.generated.resources.login_accept
import course.shared.generated.resources.home_retry
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    navigateToHome: () -> Unit = {},
    navigateToTransactions: () -> Unit = {},
    navigateToBudget: () -> Unit = {},
    navigateToGoals: () -> Unit = {},
    onOptionsClick: (() -> Unit)? = null,
    onTransactionClick: ((TransactionItemModel) -> Unit)? = null,
) {
    val homeUiState by viewModel.homeUiState.collectAsStateWithLifecycle()
    val homeUiEvent by viewModel.homeUiEvent.collectAsStateWithLifecycle()

    SafeScreenContainer {
        MenuScreen(
            activeOption = HomeMenuOption.HOME,
            navigateToHome = navigateToHome,
            navigateToTransactions = navigateToTransactions,
            navigateToBudget = navigateToBudget,
            navigateToGoals = navigateToGoals,
        ) { onMenuSelected ->
            HomeContainer(
                uiState = homeUiState,
                onViewAllClick = { onMenuSelected(HomeMenuOption.TRANSACTIONS) },
                onOptionsClick = onOptionsClick,
                onTransactionClick = onTransactionClick,
            )
        }
        Loader(isLoading = homeUiState.isLoading)
    }

    val message = when (val event = homeUiEvent) {
        HomeUiEvent.Idle -> null
        is HomeUiEvent.ShowErrorDialog -> stringResource(event.message)
        is HomeUiEvent.ShowErrorStringDialog -> event.message
    }
    if (message != null) {
        DialogCustom(
            errorDialog = ErrorDialogModel(
                title = stringResource(Res.string.error),
                message = message,
                primaryButtonText = stringResource(Res.string.home_retry),
                secondaryButtonText = stringResource(Res.string.login_accept),
            ),
            titleTextColor = AppTheme.colors.text.black,
            messageTextColor = AppTheme.colors.text.black,
            primaryButtonBackgroundColor = AppTheme.colors.backgrounds.blue,
            primaryButtonTextColor = AppTheme.colors.text.white,
            onPrimaryButtonClick = { viewModel.loadDashboard() },
            onSecondaryButtonClick = { viewModel.clearEvent() },
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun HomeScreenPreview() {
    SafeScreenContainerTest {
        MenuContainer(uiState = MenuUiState(), onMenuSelected = {}) {
            HomeContainer(uiState = homeDataMock)
        }
    }
}
