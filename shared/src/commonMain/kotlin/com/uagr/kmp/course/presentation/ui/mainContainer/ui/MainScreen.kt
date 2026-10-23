package com.uagr.kmp.course.presentation.ui.mainContainer.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer

@Composable
fun MainScreen() {
    SafeScreenContainer {
        MainContainer()
    }
}
@Preview(
    showBackground = true
)
@Composable
private fun HomeScreenPreview(){
    SafeScreenContainer {
        MainScreen()
    }
}