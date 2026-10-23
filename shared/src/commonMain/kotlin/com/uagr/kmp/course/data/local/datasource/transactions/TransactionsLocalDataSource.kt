package com.uagr.kmp.course.data.local.datasource.transactions

import com.uagr.kmp.course.domain.model.transactions.TransactionsModel

interface TransactionsLocalDataSource {
    suspend fun insertTransactions(transaction: TransactionsModel)
    suspend fun getTransactions(): TransactionsModel?
}