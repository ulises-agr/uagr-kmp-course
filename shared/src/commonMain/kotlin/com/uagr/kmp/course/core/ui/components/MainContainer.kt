package com.uagr.kmp.course.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun MainContainerScreen(
    modifier: Modifier = Modifier,
    systemColor: Color = MaterialTheme.colorScheme.primaryContainer,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier
            .background(systemColor)
            .fillMaxSize()
            .navigationBarsPadding()
            .safeContentPadding(),
    ) {
        Box(
            modifier = modifier
                .background(systemColor)
                .fillMaxSize()
                .safeContentPadding(),
        ) {
            content()
        }
    }
}