package com.uagr.kmp.course.component.Loader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.uagr.kmp.course.theme.AppTheme
import com.uagr.kmp.course.theme.Dimens

@Composable
fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = AppTheme.colors.backgrounds.transparentBlackBackground),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = AppTheme.colors.button.enable,
            strokeWidth = Dimens.height4
        )
    }
}