package com.uagr.kmp.course.presentation.ui.goals.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.ui.buget.ui.BudgetContainer

@Composable
fun GoalsScreen() {
    SafeScreenContainer {
        GoalsContainer()
    }
}
@Preview(
    showBackground = true
)
@Composable
private fun HomeScreenPreview(){
    SafeScreenContainer {
        GoalsScreen()
    }
}