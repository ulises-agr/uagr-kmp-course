package com.uagr.kmp.course.presentation.ui.home.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.dialog.DialogCustom
import com.uagr.kmp.course.presentation.component.loader.Loader
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.ui.home.viewmodel.HomeUiEvent
import com.uagr.kmp.course.presentation.ui.home.viewmodel.HomeViewModel
import com.uagr.kmp.course.utils.flow.CollectWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
) {
    val uiState by viewModel.homeUiState.collectAsStateWithLifecycle()

    viewModel.homeUiEvent.CollectWithLifecycle { event ->
        when (event) {
            is HomeUiEvent.Idle -> {}
            is HomeUiEvent.Success -> {
                viewModel.resetUiEvent()
            }
        }
    }

    SafeScreenContainer {
        HomeContainer(
            name = uiState.name,
            dashBoardModel = uiState.dashBoardModel
        )
        Loader(isLoading = uiState.isLoading)
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
fun HomeScreenPreview() {
    SafeScreenContainerTest {
        HomeContainer()
    }
}
