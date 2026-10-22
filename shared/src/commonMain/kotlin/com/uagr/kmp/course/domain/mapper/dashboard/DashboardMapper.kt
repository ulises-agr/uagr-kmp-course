/*
// DashboardMapper.kt
// Created by Karla Judith Cervantes Higareda on 08/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.mapper.dashboard

import com.uagr.kmp.course.data.network.model.response.dashboard.DashboardResponse
import com.uagr.kmp.course.domain.model.dashboard.DashboardCategoryModel
import com.uagr.kmp.course.domain.model.dashboard.DashboardGoalModel
import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
import com.uagr.kmp.course.domain.model.dashboard.DashboardTransactionModel

fun DashboardResponse.toDomain(): DashboardModel = DashboardModel(
    totalBalance = totalBalance.orEmpty(),
    monthlyIncome = monthlyIncome.orEmpty(),
    monthlyExpenses = monthlyExpenses.orEmpty(),
    monthlySavings = monthlySavings.orEmpty(),
    savingsRate = savingsRate.orEmpty(),
    budgetUsage = budgetUsage.orEmpty(),
    recentTransactions = recentTransactions.map { transaction ->
        DashboardTransactionModel(
            id = transaction.id.orEmpty(),
            accountId = transaction.accountId.orEmpty(),
            categoryId = transaction.categoryId.orEmpty(),
            type = transaction.type.orEmpty(),
            amount = transaction.amount.orEmpty(),
            currency = transaction.currency.orEmpty(),
            description = transaction.description.orEmpty(),
            notes = transaction.notes.orEmpty(),
            transactionDate = transaction.transactionDate.orEmpty(),
            createdAt = transaction.createdAt.orEmpty(),
            updatedAt = transaction.updatedAt.orEmpty(),
            version = transaction.version ?: 0,
        )
    },
    topExpenseCategories = topExpenseCategories.map { category ->
        DashboardCategoryModel(
            categoryId = category.categoryId.orEmpty(),
            categoryName = category.categoryName.orEmpty(),
            amount = category.amount.orEmpty(),
            percentage = category.percentage.orEmpty(),
        )
    },
    goals = goals.map { goal ->
        DashboardGoalModel(
            id = goal.id.orEmpty(),
            name = goal.name.orEmpty(),
            targetAmount = goal.targetAmount.orEmpty(),
            currentAmount = goal.currentAmount.orEmpty(),
            targetDate = goal.targetDate.orEmpty(),
            status = goal.status.orEmpty(),
            createdAt = goal.createdAt.orEmpty(),
            updatedAt = goal.updatedAt.orEmpty(),
            version = goal.version ?: 0,
        )
    },
)
