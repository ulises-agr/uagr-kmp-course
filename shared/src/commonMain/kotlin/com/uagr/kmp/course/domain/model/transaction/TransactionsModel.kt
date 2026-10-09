/*
 * TransactionsModel.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.model.transaction

data class TransactionsModel(
    val items: List<TransactionModel> = emptyList(),
    val pagination: PaginationModel = PaginationModel(),
)
