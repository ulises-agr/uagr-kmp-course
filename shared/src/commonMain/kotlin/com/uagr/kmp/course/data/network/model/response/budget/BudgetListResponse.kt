package com.uagr.kmp.course.data.network.model.response.budget

import com.uagr.kmp.course.data.network.model.response.base.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class BudgetListResponse(
    val data: List<BudgetListResult>? = emptyList()
): BaseResponse()
@Serializable
data class BudgetListResult(
    val id: String? = null,
    val category_id: String? = null,
    val amount: String? = null,
    val period: String? = null,
    val start_date: String? = null,
    val end_date: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val version: Int? = null,
)