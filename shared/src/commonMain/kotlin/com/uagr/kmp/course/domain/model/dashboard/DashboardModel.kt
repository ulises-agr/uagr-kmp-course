/*
// DashboardModel.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.model.dashboard

data class DashboardModel(
    val totalBalance: String,
    val monthlyIncome: String,
    val monthlyExpenses: String,
    val monthlySavings: String,
    val savingsRate: String,
    val budgetUsage: String,
    val recentTransactions: List<DashboardTransactionModel?>,
    val topExpenseCategories: List<DashboardCategoryModel?>,
    val goals: List<DashboardGoalModel?>,
)
