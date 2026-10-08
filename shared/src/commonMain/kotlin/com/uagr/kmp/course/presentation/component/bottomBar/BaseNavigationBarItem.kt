package com.uagr.kmp.course.presentation.component.bottomBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.theme.AppTheme


@Composable
fun <T> RowScope.BaseNavigationBarItem(
    item: NavigationBarItemModel<T>,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val colorIndicator: Color = if (selected) AppTheme.colors.backgrounds.blueSoft else Color.Transparent
    val colorText: Color = if (selected) AppTheme.colors.text.blue else Color.Black
    NavigationBarItem(
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = colorText,
            selectedTextColor = colorText,
            indicatorColor = colorIndicator,
            unselectedIconColor = Color.Unspecified,
            unselectedTextColor = Color.Unspecified,
            disabledIconColor = Color.Unspecified,
            disabledTextColor = Color.Unspecified,
        ),
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = item.icon,
                contentDescription = item.label,
            )
        },
        label = {
            Text(
                text = item.label,
                fontSize = 10.sp,
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun BaseBaseNavigationBarItemPreview() {
    SafeScreenContainerTest {
        NavigationBar {
            BaseNavigationBarItem(
                item = NavigationBarItemModel(
                    id = "home",
                    label = "Lorem ipsum",
                    icon = Icons.Default.Home,
                ),
                selected = true,
                onClick = {},
            )
        }
    }
}