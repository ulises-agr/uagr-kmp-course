package com.uagr.kmp.course.presentation.ui.goals.viewmodel

import com.uagr.kmp.course.presentation.ui.transactions.viewModel.TransactionsUiEvent

sealed class GoalsUiEvent {
    internal data object Idle : GoalsUiEvent()
    data class ShowVersionInfoDialog(val message: String) : GoalsUiEvent()
}