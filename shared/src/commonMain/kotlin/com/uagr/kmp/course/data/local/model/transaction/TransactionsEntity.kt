package com.uagr.kmp.course.data.local.model.transaction

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.uagr.kmp.course.domain.model.transactions.TransactionType

@Entity(tableName = "transactions")
data class TransactionsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val account_id: String?,
    val category_id: String?,
    val type: TransactionType?,
    val amount: String?,
    val currency: String?,
    val description: String?,
    val notes: String?,
    val transaction_date: String?,
    val created_at: String?,
    val updated_at: String?,
    val version: Int?
)