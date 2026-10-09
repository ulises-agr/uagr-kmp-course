/*
 * DashboardModel.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.model.dashboard

import com.uagr.kmp.course.domain.model.transaction.TransactionModel

data class DashboardModel(
    val totalBalance: String = "",
    val monthlyIncome: String = "",
    val monthlyExpenses: String = "",
    val monthlySavings: String = "",
    val savingsRate: String = "",
    val budgetUsage: String = "",
    val recentTransactions: List<TransactionModel> = emptyList(),
    val topExpenseCategories: List<String> = emptyList(),
    val goals: List<String> = emptyList(),
)
