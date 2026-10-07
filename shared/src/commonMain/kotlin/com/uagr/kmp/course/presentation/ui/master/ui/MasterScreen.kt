package com.uagr.kmp.course.presentation.ui.master.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Payments
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import com.uagr.kmp.course.presentation.component.bottomBar.NavigationBarItemModel
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.dialog.DialogCustom
import com.uagr.kmp.course.presentation.component.loader.Loader
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.ui.budget.navigation.BudgetTab
import com.uagr.kmp.course.presentation.ui.goals.navigation.GoalsTab
import com.uagr.kmp.course.presentation.ui.home.navigation.HomeTab
import com.uagr.kmp.course.presentation.ui.master.viewmodel.MasterTabs
import com.uagr.kmp.course.presentation.ui.master.viewmodel.MasterUiEvent
import com.uagr.kmp.course.presentation.ui.master.viewmodel.MasterViewModel
import com.uagr.kmp.course.presentation.ui.movements.navigation.MovementsTab
import com.uagr.kmp.course.utils.flow.CollectWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MasterScreen(
    viewModel: MasterViewModel = koinViewModel(),
) {
    val masterUiState by viewModel.masterUiState.collectAsStateWithLifecycle()

    viewModel.masterUiEvent.CollectWithLifecycle { event ->
        when (event) {
            is MasterUiEvent.Idle -> {
                viewModel.resetUiEvent()
            }

            is MasterUiEvent.Success -> {
                viewModel.resetUiEvent()
            }
        }
    }

    val tabNavigator = LocalTabNavigator.current
    SafeScreenContainer {
        MasterContainer(
            masterTab = masterUiState.currentTab,
            navigationItems = masterUiState.navigationItems,
            updateCurrentTab = { tab ->
                when (tab) {
                    MasterTabs.HOME -> {
                        tabNavigator.current = HomeTab
                    }

                    MasterTabs.MOVEMENTS -> {
                        tabNavigator.current = MovementsTab
                    }

                    MasterTabs.BUDGET -> {
                        tabNavigator.current = BudgetTab
                    }

                    MasterTabs.GOALS -> {
                        tabNavigator.current = GoalsTab
                    }
                }
            },
            content = {
                CurrentTab()
            }
        )

        Loader(isLoading = masterUiState.isLoading)

        DialogCustom(
            errorDialog = masterUiState.errorDialog,
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
fun MasterScreenPreview() {
    SafeScreenContainerTest {
        val tabNavigator = LocalTabNavigator.current
        val items = listOf(
            NavigationBarItemModel(
                id = MasterTabs.HOME,
                label = "Home",
                icon = Icons.Default.Home,
            ),
            NavigationBarItemModel(
                id = MasterTabs.MOVEMENTS,
                label = "Movimientos",
                icon = Icons.Default.Payments,
            ),
            NavigationBarItemModel(
                id = MasterTabs.BUDGET,
                label = "Presupuestos",
                icon = Icons.Default.AccountBalanceWallet,
            ),
            NavigationBarItemModel(
                id = MasterTabs.GOALS,
                label = "Metas",
                icon = Icons.Default.Flag,
            )
        )
        MasterContainer(
            navigationItems = items,
            updateCurrentTab = { tab ->
                when (tab) {
                    MasterTabs.HOME -> {
                        tabNavigator.current = HomeTab
                    }

                    MasterTabs.MOVEMENTS -> {
                        tabNavigator.current = MovementsTab
                    }

                    MasterTabs.BUDGET -> {
                        tabNavigator.current = BudgetTab
                    }

                    MasterTabs.GOALS -> {
                        tabNavigator.current = GoalsTab
                    }
                }
            },
            content = { CurrentTab() })
    }
}
