package com.uagr.kmp.course.presentation.ui.master.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.bottomBar.BaseNavigationBar
import com.uagr.kmp.course.presentation.component.bottomBar.NavigationBarItemModel
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.ui.master.navigation.MasterContent
import com.uagr.kmp.course.presentation.ui.master.viewmodel.MasterTabs
import com.uagr.kmp.course.presentation.ui.master.viewmodel.MasterUiState

@Composable
fun MasterContainer(
    uiState: MasterUiState,
    navigationItems: List<NavigationBarItemModel<MasterTabs>>,
    updateCurrentTab: (MasterTabs) -> Unit,
    content: @Composable () -> Unit,
) {
    Scaffold(
        bottomBar = {
            BaseNavigationBar(
                items = navigationItems,
                selectedItem = uiState.currentTab,
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

    }
}
