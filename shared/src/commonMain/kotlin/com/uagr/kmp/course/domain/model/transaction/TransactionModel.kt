/*
 * TransactionModel.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.model.transaction

data class TransactionModel(
    val id: String = "",
    val accountId: String = "",
    val categoryId: String = "",
    val type: String = "",
    val amount: String = "",
    val currency: String = "",
    val description: String = "",
    val notes: String = "",
    val transactionDate: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
    val version: Int = 0,
)
