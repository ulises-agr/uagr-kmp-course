
package com.uagr.kmp.course.presentation.component.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

enum class HomeTab {
    HOME,
    MOVEMENTS,
    BUDGET,
    GOALS
}

@Composable
private fun BottomTabLabel(text: String) {
    Text(
        text = text,
        fontSize = Dimens.textSizeExtraSmall,
        maxLines = 1,
        softWrap = false
    )
}

@Composable
fun BottomNavigationBar(
    selectedTab: HomeTab,
    onTabSelected: (HomeTab) -> Unit
) {
    val tabColors = NavigationBarItemDefaults.colors(
        selectedIconColor = AppTheme.colors.backgrounds.actionBlue,
        selectedTextColor = AppTheme.colors.backgrounds.actionBlue,
        indicatorColor = AppTheme.colors.backgrounds.lightBlue,
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
    )

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.padding16)
            .padding(bottom = Dimens.padding24)
            .height(Dimens.height80)
            .clip(RoundedCornerShape(Dimens.corner24)),
        containerColor = AppTheme.colors.backgrounds.white,
        tonalElevation = Dimens.elevation2,
        windowInsets = WindowInsets(0, 0, 0, 0)
    ) {
        NavigationBarItem(
            selected = selectedTab == HomeTab.HOME,
            onClick = { onTabSelected(HomeTab.HOME) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Inicio"
                )
            },
            label = { BottomTabLabel("Inicio") },
            colors = tabColors
        )

        NavigationBarItem(
            selected = selectedTab == HomeTab.MOVEMENTS,
            onClick = { onTabSelected(HomeTab.MOVEMENTS) },
            icon = {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Movimientos"
                )
            },
            label = { BottomTabLabel("Movimientos") },
            colors = tabColors
        )

        NavigationBarItem(
            selected = selectedTab == HomeTab.BUDGET,
            onClick = { onTabSelected(HomeTab.BUDGET) },
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountBalanceWallet,
                    contentDescription = "Presupuesto"
                )
            },
            label = { BottomTabLabel("Presupuesto") },
            colors = tabColors
        )

        NavigationBarItem(
            selected = selectedTab == HomeTab.GOALS,
            onClick = { onTabSelected(HomeTab.GOALS) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Flag,
                    contentDescription = "Metas"
                )
            },
            label = { BottomTabLabel("Metas") },
            colors = tabColors
        )
    }
}
