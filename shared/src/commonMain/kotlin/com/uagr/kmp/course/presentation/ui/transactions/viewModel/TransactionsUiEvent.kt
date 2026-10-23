package com.uagr.kmp.course.presentation.ui.transactions.viewModel

sealed class TransactionsUiEvent {
    internal data object Idle : TransactionsUiEvent()
    data class ShowVersionInfoDialog(val message: String) : TransactionsUiEvent()
}