/*
// DashboardSummaryModel.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.model.dashboard

import com.uagr.kmp.course.domain.model.base.TransactionItemModel

data class DashboardSummaryModel(
    val totalBalance: String,
    val incomeAmount: String,
    val expensesAmount: String,
    val monthlyExpensesAmount: String,
    val savingsAmount: String,
    val budgetUsage: String,
    val budgetWithinLimit: Boolean,
    val savingsRate: String,
    val positiveSavingsRate: Boolean,
    val transactions: List<TransactionItemModel>,
)
