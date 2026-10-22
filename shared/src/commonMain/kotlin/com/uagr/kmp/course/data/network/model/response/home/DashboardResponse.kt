package com.uagr.kmp.course.data.network.model.response.home

import kotlinx.serialization.Serializable

@Serializable
data class DashboardResponse(
    val total_balance: String? = null,
    val monthly_income: String? = null,
    val monthly_expenses: String? = null,
    val monthly_savings: String? = null,
    val savings_rate: String? = null,
    val budget_usage: String? = null,
    val recent_transactions: List<TransactionResponse>? = emptyList(),
    val top_expense_categories: List<TopExpenseCategoryResponse>? = emptyList(),
    val goals: List<GoalResponse>? = emptyList(),
)

@Serializable
data class TransactionResponse(
    val id: String? = null,
    val account_id: String? = null,
    val category_id: String? = null,
    val type: String? = null,
    val amount: String? = null,
    val currency: String? = null,
    val description: String? = null,
    val notes: String? = null,
    val transaction_date: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val version: Int? = null,
)

@Serializable
data class TopExpenseCategoryResponse(
    val category_id: String? = null,
    val category_name: String? = null,
    val amount: String? = null,
    val percentage: String? = null,
)

@Serializable
data class GoalResponse(
    val id: String? = null,
    val name: String? = null,
    val target_amount: String? = null,
    val current_amount: String? = null,
    val target_date: String? = null,
    val status: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val version: Int? = null,
)