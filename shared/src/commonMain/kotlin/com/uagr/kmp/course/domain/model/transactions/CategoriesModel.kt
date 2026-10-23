package com.uagr.kmp.course.domain.model.transactions

data class CategoriesDataModel(
    val id: String,
    val name: String,
    val type: TransactionType,
    val created_at: String
)
enum class TransactionType {
    INCOME,
    EXPENSE,

    NONE
}