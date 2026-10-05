package com.uagr.kmp.course.presentation.ui.budget.viewmodel

sealed class BudgetUiEvent {
    internal data object Idle : BudgetUiEvent()
    data object Success : BudgetUiEvent()
}
