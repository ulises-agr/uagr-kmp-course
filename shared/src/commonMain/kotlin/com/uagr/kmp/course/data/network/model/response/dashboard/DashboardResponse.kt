/*
// DashboardResponse.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.data.network.model.response.dashboard

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DashboardResponse(
    @SerialName("total_balance") val totalBalance: String?,
    @SerialName("monthly_income") val monthlyIncome: String?,
    @SerialName("monthly_expenses") val monthlyExpenses: String?,
    @SerialName("monthly_savings") val monthlySavings: String?,
    @SerialName("savings_rate") val savingsRate: String?,
    @SerialName("budget_usage") val budgetUsage: String?,
    @SerialName("recent_transactions") val recentTransactions: List<DashboardTransactionResponse> = emptyList(),
    @SerialName("top_expense_categories") val topExpenseCategories: List<DashboardCategoryResponse> = emptyList(),
    val goals: List<DashboardGoalResponse> = emptyList(),
)
