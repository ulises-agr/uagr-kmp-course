/*
 * HeatScreen.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.card.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.presentation.welcome.viewmodel.CardViewModel
import com.tarifchakder.ktoast.ToastState
import com.uagr.kmp.course.component.Loader.Loader
import com.uagr.kmp.course.component.Toast.Toast
import com.uagr.kmp.course.component.container.SafeScreenContainer
import com.uagr.kmp.course.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.theme.AppTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CardScreen() {
    
    val viewModel : CardViewModel = koinViewModel()
    val cardUiEvent by viewModel.cardUiEvent.collectAsStateWithLifecycle()
    val cardUiState by viewModel.cardUiState.collectAsStateWithLifecycle()
    val toastState = ToastState()
    
    SafeScreenContainer(
        backgroundColor = AppTheme.colors.backgrounds.backgroundColor
    ) {
        
        Box(modifier = Modifier.fillMaxSize()) {
            CardContainer(
                onClickButton = {
                    viewModel.onClickButton()
                },
                onClickIcon = {
                    viewModel.onClickIcon()
                }
            )
            
            Toast(
                modifier = Modifier,
                toastState = toastState
            )
            
            if (cardUiState.showLoader) {
                Loader()
            }
            
            if(cardUiState.showToast){
                viewModel.showToast(toastState = toastState)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardScreenPreview() {
    SafeScreenContainerTest {
        CardScreen()
    }
}
