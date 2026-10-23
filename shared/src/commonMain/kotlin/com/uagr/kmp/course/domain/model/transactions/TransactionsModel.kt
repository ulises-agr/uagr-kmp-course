package com.uagr.kmp.course.domain.model.transactions

data class TransactionsModel(
    val id: String,
    val account_id: String,
    val category_id: String,
    val type: TransactionType,
    val amount: String,
    val currency: String,
    val description: String,
    val notes: String,
    val transaction_date: String,
    val created_at: String,
    val updated_at: String,
    val version: Int,
    val categoryName: String
)