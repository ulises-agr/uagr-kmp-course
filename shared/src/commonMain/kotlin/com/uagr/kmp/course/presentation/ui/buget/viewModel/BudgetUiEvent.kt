package com.uagr.kmp.course.presentation.ui.buget.viewModel

sealed class BudgetUiEvent {
    internal data object Idle : BudgetUiEvent()
    data class ShowVersionInfoDialog(val message: String) : BudgetUiEvent()
}