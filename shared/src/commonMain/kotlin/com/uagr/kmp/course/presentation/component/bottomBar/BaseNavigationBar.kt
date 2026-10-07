package com.uagr.kmp.course.presentation.component.bottomBar

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest

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

    }
}