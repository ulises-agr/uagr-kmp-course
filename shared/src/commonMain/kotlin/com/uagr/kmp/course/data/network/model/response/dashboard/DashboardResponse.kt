package com.uagr.kmp.course.data.network.model.response.dashboard

import com.uagr.kmp.course.data.network.model.response.base.BaseResponse
import com.uagr.kmp.course.data.network.model.response.transactions.TransactionsResponse
import kotlinx.serialization.Serializable

@Serializable
data class DashboardResponse(
    var total_balance: String? = null,
    var monthly_income: String? = null,
    var monthly_expenses: String? = null,
    var monthly_savings: String? = null,
    var savings_rate: String? = null,
    var budget_usage: String? = null,
    var recent_transactions: List<RecentTransactionResponse>? = null,
    var top_expense_categories: List<TopExpenseCategoryResponse>? = null,
    var goals: List<GoalsResponse>? = null
): BaseResponse()