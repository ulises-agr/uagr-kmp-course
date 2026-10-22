package com.uagr.kmp.course.domain.model.home

data class DashboardModel(
    val totalBalance: String,
    val monthlyIncome: String,
    val monthlyExpenses: String,
    val monthlySavings: String,
    val recentTransactions: List<TransactionModel>,
)

data class TransactionModel(
    val id: String,
    val description: String,
    val categoryOrSubtitle: String,
    val amountFormatted: String,
    val isExpense: Boolean,
)