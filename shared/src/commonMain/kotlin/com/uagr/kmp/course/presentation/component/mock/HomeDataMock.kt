/*
// HomeDataMock.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.component.mock

import com.uagr.kmp.course.domain.model.base.TransactionItemModel
import com.uagr.kmp.course.presentation.component.list.TransactionType
import com.uagr.kmp.course.presentation.ui.home.viewmodel.HomeUiState

val transactionListMock: List<TransactionItemModel> = listOf(
    TransactionItemModel("1", "Supermercado", "Hoy · Alimentación", "-$860", TransactionType.EXPENSE),
    TransactionItemModel("2", "Nómina", "Ayer · Ingreso", "+$15,500", TransactionType.INCOME),
    TransactionItemModel("3", "Internet", "18 sep · Servicios", "-$599", TransactionType.EXPENSE),
)

val homeDataMock: HomeUiState = HomeUiState(
    totalBalance = "$24,860.00",
    incomeAmount = "+$18,500",
    expensesAmount = "-$8,460",
    monthlyExpensesAmount = "$8,460",
    savingsAmount = "$3,120",
    expensesChange = "12%",
    expensesIncreasing = false,
    expensesPositiveChange = true,
    savingsChange = "8%",
    savingsIncreasing = true,
    savingsPositiveChange = true,
    transactions = transactionListMock,
)
