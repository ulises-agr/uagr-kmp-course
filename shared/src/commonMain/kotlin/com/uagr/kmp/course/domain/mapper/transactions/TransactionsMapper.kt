package com.uagr.kmp.course.domain.mapper.transactions

import com.uagr.kmp.course.data.local.model.transaction.TransactionsEntity
import com.uagr.kmp.course.data.network.model.response.transactions.TransactionsResponse
import com.uagr.kmp.course.domain.model.transactions.TransactionType
import com.uagr.kmp.course.domain.model.transactions.TransactionsModel

fun TransactionsResponse.toDomain(): TransactionsModel =
    TransactionsModel(
        id = id.orEmpty(),
        account_id = account_id.orEmpty(),
        category_id = category_id.orEmpty(),
        type = type?.toTransactionType() ?: TransactionType.NONE,
        amount = amount.orEmpty(),
        currency = currency.orEmpty(),
        description = description.orEmpty(),
        notes = notes.orEmpty(),
        transaction_date = transaction_date.orEmpty(),
        created_at = created_at.orEmpty(),
        updated_at = updated_at.orEmpty(),
        version = version ?: 1,
        categoryName = ""
    )
fun TransactionsModel.toEntity(): TransactionsEntity =
    TransactionsEntity(
        id = id,
        account_id = account_id,
        category_id = category_id,
        type =  type,
        amount = amount,
        currency = currency,
        description = description,
        notes = notes,
        transaction_date = transaction_date,
        created_at = created_at,
        updated_at = updated_at,
        version = version,
    )

fun TransactionsEntity.toDomain(): TransactionsModel =
    TransactionsModel(
        id = id,
        account_id = account_id.orEmpty(),
        category_id = category_id.orEmpty(),
        type = type ?: TransactionType.NONE,
        amount = amount.orEmpty(),
        currency = currency.orEmpty(),
        description = description.orEmpty(),
        notes = notes.orEmpty(),
        transaction_date = transaction_date.orEmpty(),
        created_at = created_at.orEmpty(),
        updated_at = updated_at.orEmpty(),
        version = version ?: 1,
        categoryName = ""
    )