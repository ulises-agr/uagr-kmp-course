/*
// HomeUiState.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.home.viewmodel

import com.uagr.kmp.course.domain.model.base.TransactionItemModel

data class HomeUiState(
    val totalBalance: String = "--",
    val incomeAmount: String = "--",
    val expensesAmount: String = "--",
    val monthlyExpensesAmount: String = "--",
    val savingsAmount: String = "--",
    val expensesChange: String = "--",
    val expensesIncreasing: Boolean = false,
    val expensesPositiveChange: Boolean = true,
    val savingsChange: String = "--",
    val savingsIncreasing: Boolean = true,
    val savingsPositiveChange: Boolean = true,
    val transactions: List<TransactionItemModel> = emptyList(),
)

