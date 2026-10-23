package com.uagr.kmp.course.domain.mapper.transactions

//import com.uagr.kmp.course.data.network.model.response.transactions.CategorieResult
import com.uagr.kmp.course.domain.model.transactions.CategoriesDataModel
import com.uagr.kmp.course.domain.model.transactions.TransactionType
import kotlin.String
/*
fun CategorieResult.toDomian(): CategoriesDataModel =
    CategoriesDataModel(
        id = id.orEmpty(),
        name = name.orEmpty(),
        type = type?.toTransactionType() ?: TransactionType.NONE,
        created_at = created_at.orEmpty()
    )

 */
fun String.toTransactionType(): TransactionType {
    return when (this.uppercase()) {
        "INCOME" -> TransactionType.INCOME
        "EXPENSE" -> TransactionType.EXPENSE
        else -> throw IllegalArgumentException(
            "Unknown transaction type: $this"
        )
    }
}