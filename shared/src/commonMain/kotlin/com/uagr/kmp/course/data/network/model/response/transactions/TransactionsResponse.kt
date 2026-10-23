package com.uagr.kmp.course.data.network.model.response.transactions

import com.uagr.kmp.course.data.network.model.response.base.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class TransactionsResponse(
    val id: String? = null,
    val account_id: String? = null,
    val category_id: String? = null,
    val type: String? = null,
    val amount: String? = null,
    val currency: String? = null,
    val description: String? = null,
    val notes: String? = null,
    val transaction_date: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val version: Int? = null
): BaseResponse()