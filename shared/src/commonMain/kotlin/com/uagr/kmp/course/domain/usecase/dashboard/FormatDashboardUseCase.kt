/*
// FormatDashboardUseCase.kt
// Created by Karla Judith Cervantes Higareda on 08/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.usecase.dashboard

import com.uagr.kmp.course.domain.model.base.TransactionItemModel
import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
import com.uagr.kmp.course.domain.model.dashboard.DashboardSummaryModel
import com.uagr.kmp.course.presentation.component.list.TransactionType
import org.koin.core.annotation.Factory

@Factory
class FormatDashboardUseCase {
    operator fun invoke(
        dashboard: DashboardModel
    ): DashboardSummaryModel {
        return DashboardSummaryModel(
            totalBalance = dashboard.totalBalance,
            incomeAmount = dashboard.monthlyIncome,
            expensesAmount = dashboard.monthlyExpenses,
            monthlyExpensesAmount = dashboard.monthlyExpenses,
            savingsAmount = dashboard.monthlySavings,
            budgetUsage = dashboard.budgetUsage,
            budgetWithinLimit = dashboard.budgetUsage.toDoubleOrNull()?.let { it in 0.0..100.0 } ?: false,
            savingsRate = dashboard.savingsRate,
            positiveSavingsRate = !dashboard.savingsRate.startsWith("-"),
            transactions = dashboard.recentTransactions.filterNotNull().map { transaction ->
                TransactionItemModel(
                    id = transaction.id,
                    title = transaction.description,
                    subtitle = transaction.transactionDate,
                    amount = transaction.amount,
                    type = if (transaction.type == TransactionType.INCOME.name) {
                        TransactionType.INCOME
                    } else {
                        TransactionType.EXPENSE
                    },
                )
            },
        )
    }

}
