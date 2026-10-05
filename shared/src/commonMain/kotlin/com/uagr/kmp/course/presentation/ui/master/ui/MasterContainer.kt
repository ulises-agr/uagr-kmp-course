package com.uagr.kmp.course.presentation.ui.master.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uagr.kmp.course.presentation.component.bottomBar.BottomBar
import com.uagr.kmp.course.presentation.component.bottomBar.BottomBarItem
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.ui.budget.ui.BudgetScreen
import com.uagr.kmp.course.presentation.ui.goals.ui.GoalsScreen
import com.uagr.kmp.course.presentation.ui.home.ui.HomeScreen
import com.uagr.kmp.course.presentation.ui.master.navigation.MasterRoute
import com.uagr.kmp.course.presentation.ui.master.viewmodel.MasterTabs
import com.uagr.kmp.course.presentation.ui.movements.ui.MovementsScreen
import org.jetbrains.compose.resources.painterResource

@Composable
fun MasterContainer(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    tabs: List<MasterTabs> = MasterTabs.entries,
    currentTab: MasterTabs = MasterTabs.HOME,
    onTabSelected: (MasterTabs) -> Unit = {},
) {
    val bottomBarItems = remember(tabs) {
        tabs.map { tab ->
            BottomBarItem(
                title = tab.title,
                route = tab.route,
                icon = {
                    Icon(
                        painter = painterResource(tab.icon),
                        contentDescription = tab.title,
                    )
                },
            )
        }
    }

    LaunchedEffect(currentTab) {
        if (navController.currentDestination?.hasRoute(currentTab.route::class) != true) {
            navController.navigate(currentTab.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(
                navController = navController,
                items = bottomBarItems,
                onItemClick = { route ->
                    tabs.find { it.route == route }?.let { tab ->
                        onTabSelected(tab)
                    }
                },
            )
        },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = MasterRoute.Home,
            modifier = Modifier.padding(paddingValues),
        ) {
            composable<MasterRoute.Home> {
                HomeScreen()
            }

            composable<MasterRoute.Movements> {
                MovementsScreen()
            }

            composable<MasterRoute.Budget> {
                BudgetScreen()
            }

            composable<MasterRoute.Goals> {
                GoalsScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MasterContainerPreview() {
    SafeScreenContainerTest {
        MasterContainer()
    }
}
