package com.uagr.kmp.course.presentation.component.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.ui.master.viewmodel.MasterTabs

data class NavigationBarItemModel<T>(
    val id: T,
    val label: String,
    val icon: ImageVector,
)

@Composable
fun <T> BaseNavigationBar(
    items: List<NavigationBarItemModel<T>>,
    selectedItem: T,
    onItemSelected: (T) -> Unit,
) {
    NavigationBar {
        items.forEach { item ->
            BaseNavigationBarItem(
                item = item,
                selected = item.id == selectedItem,
                onClick = {
                    onItemSelected(item.id)
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BaseNavigationBarPreview() {
    SafeScreenContainerTest {
        var selectedTab by remember { mutableStateOf(MasterTabs.HOME) }
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
                id = MasterTabs.GOALS,
                label = "Presupuestos",
                icon = Icons.Default.Payments,
            ),
            NavigationBarItemModel(
                id = MasterTabs.GOALS,
                label = "Presupuestos",
                icon = Icons.Default.Payments,
            ),
        )

        BaseNavigationBar(
            items = items,
            selectedItem = selectedTab,
            onItemSelected = { tab ->
                selectedTab = tab
            },
        )
    }
}