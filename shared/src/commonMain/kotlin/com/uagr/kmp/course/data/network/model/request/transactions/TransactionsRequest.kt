package com.uagr.kmp.course.data.network.model.request.transactions

import kotlinx.serialization.Serializable

@Serializable
data class TransactionsRequest(
    val account_id: String,
    val category_id: String,
    val type: String,
    val amount: String,
    val currency: String,
    val description: String,
    val notes: String,
    val transaction_date: String
)
