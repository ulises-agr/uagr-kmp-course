package com.uagr.kmp.course.presentation.component.Loader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import com.uagr.kmp.course.utils.operators.StatusLoading

@Composable
fun Loader(
    modifier: Modifier = Modifier,
    currentState: StatusLoading = StatusLoading.HIDE_LOADING,
) {
    if(currentState == StatusLoading.SHOW_LOADING){
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = AppTheme.colors.backgrounds.transparentBlackBackground),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = AppTheme.colors.button.enable,
                strokeWidth = Dimens.height4
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoaderPreview() {
    SafeScreenContainerTest {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimens.padding16),
        ) {
            Loader(currentState = StatusLoading.SHOW_LOADING)
        }
    }
}