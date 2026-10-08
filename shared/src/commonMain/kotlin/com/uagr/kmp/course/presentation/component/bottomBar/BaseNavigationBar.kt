package com.uagr.kmp.course.presentation.component.bottomBar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import com.uagr.kmp.course.presentation.ui.master.viewmodel.MasterTabs
import course.shared.generated.resources.Res
import course.shared.generated.resources.navigationBar_Budget
import course.shared.generated.resources.navigationBar_Goals
import course.shared.generated.resources.navigationBar_Home
import course.shared.generated.resources.navigationBar_Movements
import org.jetbrains.compose.resources.stringResource

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
    val roundCorner = RoundedCornerShape(size = Dimens.height16)
    NavigationBar (
        modifier = Modifier
            .padding(Dimens.padding16)
            .shadow(
                elevation = Dimens.height4,
                shape = roundCorner,
                clip = true
            )
            .background(
                color = AppTheme.colors.backgrounds.white,
                shape = roundCorner
            )
    ) {
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
                label = stringResource(Res.string.navigationBar_Home),
                icon = Icons.Default.Home,
            ),
            NavigationBarItemModel(
                id = MasterTabs.MOVEMENTS,
                label = stringResource(Res.string.navigationBar_Movements),
                icon = Icons.Default.Payments,
            ),
            NavigationBarItemModel(
                id = MasterTabs.GOALS,
                label = stringResource(Res.string.navigationBar_Budget),
                icon = Icons.Default.Payments,
            ),
            NavigationBarItemModel(
                id = MasterTabs.GOALS,
                label = stringResource(Res.string.navigationBar_Goals),
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