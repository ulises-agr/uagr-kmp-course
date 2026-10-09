/*
 * DashboardMapper.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.mapper.dashboard

import com.uagr.kmp.course.data.network.model.response.dashboard.DashboardResponse
import com.uagr.kmp.course.data.network.model.response.dashboard.TopExpenseCategoryResponse
import com.uagr.kmp.course.domain.mapper.transaction.toDomain
import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
import com.uagr.kmp.course.domain.model.dashboard.TopExpenseCategoryModel

fun DashboardResponse.toDomain(): DashboardModel =
    DashboardModel(
        totalBalance = total_balance.orEmpty(),
        monthlyIncome = monthly_income.orEmpty(),
        monthlyExpenses = monthly_expenses.orEmpty(),
        monthlySavings = monthly_savings.orEmpty(),
        savingsRate = savings_rate.orEmpty(),
        budgetUsage = budget_usage.orEmpty(),
        recentTransactions = recent_transactions?.map { it.toDomain() }.orEmpty(),
        topExpenseCategories = top_expense_categories?.map { it.toDomain() }.orEmpty(),
        goals = goals.orEmpty(),
    )

fun TopExpenseCategoryResponse.toDomain(): TopExpenseCategoryModel =
    TopExpenseCategoryModel(
        categoryId = category_id.orEmpty(),
        categoryName = category_name.orEmpty(),
        amount = amount.orEmpty(),
        percentage = percentage.orEmpty(),
    )
