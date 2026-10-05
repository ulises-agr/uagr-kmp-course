package com.uagr.kmp.course.presentation.ui.movements.ui

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
import com.uagr.kmp.course.presentation.ui.movements.viewmodel.MovementsUiEvent
import com.uagr.kmp.course.presentation.ui.movements.viewmodel.MovementsViewModel
import com.uagr.kmp.course.utils.flow.CollectWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovementsScreen(
    viewModel: MovementsViewModel = koinViewModel(),
) {
    val uiState by viewModel.movementsUiState.collectAsStateWithLifecycle()

    viewModel.movementsUiEvent.CollectWithLifecycle { event ->
        when (event) {
            is MovementsUiEvent.Idle -> {}
            is MovementsUiEvent.Success -> {
                viewModel.resetUiEvent()
            }
        }
    }

    SafeScreenContainer {
        MovementsContainer(
            movementsData = uiState.data,
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
fun MovementsScreenPreview() {
    SafeScreenContainerTest {
        MovementsContainer()
    }
}
