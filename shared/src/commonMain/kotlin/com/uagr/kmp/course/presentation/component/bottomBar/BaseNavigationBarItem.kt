package com.uagr.kmp.course.presentation.component.bottomBar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest


@Composable
fun <T> RowScope.BaseNavigationBarItem(
    item: NavigationBarItemModel<T>,
    selected: Boolean,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = item.icon,
                contentDescription = item.label,
            )
        },
        label = {
            Text(text = item.label)
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun BaseBaseNavigationBarItemPreview() {
    SafeScreenContainerTest {

    }
}