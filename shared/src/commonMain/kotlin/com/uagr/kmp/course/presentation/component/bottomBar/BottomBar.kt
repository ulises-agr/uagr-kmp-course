package com.uagr.kmp.course.presentation.component.bottomBar

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest

data class BottomBarItem<T : Any>(
    val title: String,
    val route: T,
    val icon: @Composable () -> Unit,
)

@Composable
fun <T : Any> BottomBar(
    navController: NavHostController,
    items: List<BottomBarItem<T>>,
    modifier: Modifier = Modifier,
    onItemClick: ((T) -> Unit)? = null,
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar(
        modifier = modifier,
    ) {
        items.forEach { item ->
            val selected = backStackEntry?.destination
                ?.hierarchy
                ?.any { destination ->
                    destination.hasRoute(item.route::class)
                } == true

            NavigationBarItem(
                selected = selected,
                onClick = {
                    onItemClick?.invoke(item.route)
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = item.icon,
                label = {
                    Text(text = item.title)
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomBarPreview() {
    SafeScreenContainerTest {
        BottomBar(
            navController = rememberNavController(),
            items = emptyList<BottomBarItem<String>>(),
        )
    }
}
