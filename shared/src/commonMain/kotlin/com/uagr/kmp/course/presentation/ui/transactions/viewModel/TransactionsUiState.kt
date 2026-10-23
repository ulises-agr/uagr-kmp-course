package com.uagr.kmp.course.presentation.ui.transactions.viewModel

import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.utils.operators.StatusLoading

data class TransactionsUiState (
    val isLoading: StatusLoading = StatusLoading.DISMISS_LOADING,
    val errorDialog: ErrorDialogModel? = null,
    val name: String = "",
    val totalBalance: String = "",
    val monthlyIncome: String = "",
    val monthlyExpenses: String = "",
    val monthlySavings: String = "",
    val savingsRate: String = ""
)