/*
 * DashboardResponse.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.model.response.dashboard

import com.uagr.kmp.course.data.network.model.response.base.BaseResponse
import com.uagr.kmp.course.data.network.model.response.transaction.TransactionResponse
import kotlinx.serialization.Serializable

@Serializable
data class DashboardResponse(
    val total_balance: String? = null,
    val monthly_income: String? = null,
    val monthly_expenses: String? = null,
    val monthly_savings: String? = null,
    val savings_rate: String? = null,
    val budget_usage: String? = null,
    val recent_transactions: List<TransactionResponse>? = null,
    val top_expense_categories: List<TopExpenseCategoryResponse>? = null,
    val goals: List<String>? = null,
) : BaseResponse()
