/*
// HomeUiState.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.home.viewmodel

import com.uagr.kmp.course.domain.model.base.TransactionItemModel
import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
import com.uagr.kmp.course.utils.operators.StatusLoading

data class HomeUiState(
    val totalBalance: String = "",
    val incomeAmount: String = "",
    val expensesAmount: String = "",
    val monthlyExpensesAmount: String = "",
    val savingsAmount: String = "",
    val expensesChange: String = "",
    val expensesIncreasing: Boolean = false,
    val expensesPositiveChange: Boolean = true,
    val savingsChange: String = "",
    val savingsIncreasing: Boolean = true,
    val savingsPositiveChange: Boolean = true,
    val transactions: List<TransactionItemModel> = emptyList(),
    val userName: String = "",
    val isLoading: StatusLoading = StatusLoading.DISMISS_LOADING,
    val dashboard: DashboardModel? = null,
)

