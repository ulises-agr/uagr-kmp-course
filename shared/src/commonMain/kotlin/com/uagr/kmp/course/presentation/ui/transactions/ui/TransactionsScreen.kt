package com.uagr.kmp.course.presentation.ui.transactions.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer

@Composable
fun TransactionsScreen() {
    SafeScreenContainer {
        TransactionsContainer()
    }
}
@Preview(
    showBackground = true
)
@Composable
private fun TransactionsScreenPreview(){
    SafeScreenContainer {
        TransactionsScreen()
    }
}