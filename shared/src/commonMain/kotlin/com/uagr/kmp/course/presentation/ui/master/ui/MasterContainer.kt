package com.uagr.kmp.course.presentation.ui.master.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.uagr.kmp.course.presentation.component.bottomBar.BaseNavigationBar
import com.uagr.kmp.course.presentation.component.bottomBar.NavigationBarItemModel
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.ui.budget.navigation.BudgetTab
import com.uagr.kmp.course.presentation.ui.goals.navigation.GoalsTab
import com.uagr.kmp.course.presentation.ui.home.navigation.HomeTab
import com.uagr.kmp.course.presentation.ui.master.navigation.MasterContent
import com.uagr.kmp.course.presentation.ui.master.viewmodel.MasterTabs
import com.uagr.kmp.course.presentation.ui.movements.navigation.MovementsTab
import course.shared.generated.resources.Res
import course.shared.generated.resources.navigationBar_Budget
import course.shared.generated.resources.navigationBar_Goals
import course.shared.generated.resources.navigationBar_Home
import course.shared.generated.resources.navigationBar_Movements
import org.jetbrains.compose.resources.stringResource

@Composable
fun MasterContainer(
    masterTab: MasterTabs = MasterTabs.HOME,
    navigationItems: List<NavigationBarItemModel<MasterTabs>> = emptyList(),
    updateCurrentTab: (MasterTabs) -> Unit,
    content: @Composable () -> Unit,
) {
    Scaffold(
        bottomBar = {
            BaseNavigationBar(
                items = navigationItems,
                selectedItem = masterTab,
                onItemSelected = updateCurrentTab,
            )
        }
    ) { paddingValues ->

        MasterContent(
            modifier = Modifier.padding(paddingValues),
            content = content,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MasterContainerPreview() {
    SafeScreenContainerTest {
        TabNavigator(HomeTab) {
            val tabNavigator = LocalTabNavigator.current
            var selectedTab by remember { mutableStateOf(MasterTabs.HOME) }
            val items = listOf(
                NavigationBarItemModel(
                    id = MasterTabs.HOME,
                    label = stringResource(Res.string.navigationBar_Home),
                    icon = Icons.Default.Home,
                ),
                NavigationBarItemModel(
                    id = MasterTabs.MOVEMENTS,
                    label = stringResource(Res.string.navigationBar_Movements),
                    icon = Icons.Default.Payments,
                ),
                NavigationBarItemModel(
                    id = MasterTabs.BUDGET,
                    label = stringResource(Res.string.navigationBar_Budget),
                    icon = Icons.Default.AccountBalanceWallet,
                ),
                NavigationBarItemModel(
                    id = MasterTabs.GOALS,
                    label = stringResource(Res.string.navigationBar_Goals),
                    icon = Icons.Default.Flag,
                ),
            )

            MasterContainer(
                masterTab = selectedTab,
                navigationItems = items,
                updateCurrentTab = { tab ->
                    selectedTab = tab
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
                content = { CurrentTab() },
            )
        }
    }
}
