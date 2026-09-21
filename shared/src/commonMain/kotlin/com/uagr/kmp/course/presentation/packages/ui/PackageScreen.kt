/*
 * PackageScreen.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.packages.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.presentation.welcome.viewmodel.PackageUiEvent
import com.tarifchakder.ktoast.ToastState
import com.uagr.kmp.course.presentation.component.Loader.Loader
import com.uagr.kmp.course.presentation.component.Toast.Toast
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.packages.viewmodel.PackageUiState
import com.uagr.kmp.course.presentation.packages.viewmodel.PackageViewModel
import com.uagr.kmp.course.presentation.theme.AppTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PackageScreen(
    viewModel: PackageViewModel = koinViewModel()
) {
    
    val packageUiEvent by viewModel.packageUiEvent.collectAsStateWithLifecycle()
    val packageUiState by viewModel.packageUiState.collectAsStateWithLifecycle()
    val toastState = ToastState()
    
    SafeScreenContainer(
        backgroundColor = AppTheme.colors.backgrounds.backgroundColor
    ) {
        /*AnimatedContent(
            targetState = packageUiState.showLoader,
            transitionSpec = { Animation.fadeInTransition(250) }
        ){ currentState ->*/
            CardContainer(
                packages = packageUiState.packages,
                onClickButton = {
                    viewModel.onClickButton()
                },
                onClickIcon = {
                    viewModel.onClickIcon()
                }
            )
            
            Loader(currentState = packageUiState.showLoader)
            
            Toast(
                modifier = Modifier,
                toastState = toastState,
                currentState = packageUiState.showToast)
        
        when(packageUiEvent){
            is PackageUiEvent.Idle -> {}
            is PackageUiEvent.ShowErrorDialog -> {
                viewModel.showToast(toastState = toastState, toastMessage = (packageUiEvent as PackageUiEvent.ShowErrorDialog).message)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PackageScreenPreview() {
    SafeScreenContainerTest {
        PackageScreen()
    }
}
