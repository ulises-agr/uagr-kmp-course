package com.uagr.kmp.course.presentation.ui.home.viewmodel

import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.model.budget.BudgetWithCategorytModel
import com.uagr.kmp.course.domain.model.dashboard.RecentTransactionModel
import com.uagr.kmp.course.utils.operators.StatusLoading
import kotlin.collections.List

data class HomeUiState (
    val isLoading: StatusLoading = StatusLoading.DISMISS_LOADING,
    val errorDialog: ErrorDialogModel? = null,
    val name: String = "",
    val totalBalance: String = "",
    val monthlyIncome: String = "",
    val monthlyExpenses: String = "",
    val monthlySavings: String = "",
    val savingsRate: String = "",
    val recentTransactions: List<RecentTransactionModel> = emptyList(),
)