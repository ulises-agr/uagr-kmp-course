package com.uagr.kmp.course.presentation.ui.master.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.dialog.DialogCustom
import com.uagr.kmp.course.presentation.component.loader.Loader
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.ui.master.viewmodel.MasterUiEvent
import com.uagr.kmp.course.presentation.ui.master.viewmodel.MasterViewModel
import com.uagr.kmp.course.utils.flow.CollectWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MasterScreen(
    viewModel: MasterViewModel = koinViewModel(),
) {
    val uiState by viewModel.masterUiState.collectAsStateWithLifecycle()

    viewModel.masterUiEvent.CollectWithLifecycle { event ->
        when (event) {
            is MasterUiEvent.Idle -> {}
            is MasterUiEvent.Success -> {
                viewModel.resetUiEvent()
            }
        }
    }

    SafeScreenContainer {
        MasterContainer(
            tabs = uiState.tabs,
            currentTab = uiState.currentTab,
            onTabSelected = { tab ->
                viewModel.updateCurrentTab(tab)
            },
        )
        Loader()
        DialogCustom(
            errorDialog = uiState.errorDialog,
            titleTextColor = AppTheme.colors.text.black,
            messageTextColor = AppTheme.colors.text.black,
            primaryButtonBackgroundColor = AppTheme.colors.primary,
            primaryButtonTextColor = AppTheme.colors.text.white,
            onPrimaryButtonClick = {
                viewModel.dismissErrorDialog()
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MasterScreenPreview() {
    SafeScreenContainerTest {
        MasterContainer()
    }
}
