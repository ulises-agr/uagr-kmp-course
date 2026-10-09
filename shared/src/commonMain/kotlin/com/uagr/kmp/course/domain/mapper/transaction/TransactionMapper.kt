/*
 * TransactionMapper.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.mapper.transaction

import com.uagr.kmp.course.data.network.model.response.transaction.PaginationResponse
import com.uagr.kmp.course.data.network.model.response.transaction.TransactionResponse
import com.uagr.kmp.course.data.network.model.response.transaction.TransactionsResponse
import com.uagr.kmp.course.domain.model.transaction.PaginationModel
import com.uagr.kmp.course.domain.model.transaction.TransactionModel
import com.uagr.kmp.course.domain.model.transaction.TransactionType
import com.uagr.kmp.course.domain.model.transaction.TransactionsModel

fun TransactionResponse.toDomain(): TransactionModel =
    TransactionModel(
        id = id.orEmpty(),
        accountId = account_id.orEmpty(),
        categoryId = category_id.orEmpty(),
        type = TransactionType.from(type),
        amount = amount.orEmpty(),
        currency = currency.orEmpty(),
        description = description.orEmpty(),
        notes = notes.orEmpty(),
        transactionDate = transaction_date.orEmpty(),
        createdAt = created_at.orEmpty(),
        updatedAt = updated_at.orEmpty(),
        version = version ?: 0,
    )

fun TransactionsResponse.toDomain(): TransactionsModel =
    TransactionsModel(
        items = items?.map { it.toDomain() }.orEmpty(),
        pagination = pagination?.toDomain() ?: PaginationModel(),
    )

fun PaginationResponse.toDomain(): PaginationModel =
    PaginationModel(
        page = page ?: 0,
        pageSize = page_size ?: 0,
        total = total ?: 0,
        pages = pages ?: 0,
    )
