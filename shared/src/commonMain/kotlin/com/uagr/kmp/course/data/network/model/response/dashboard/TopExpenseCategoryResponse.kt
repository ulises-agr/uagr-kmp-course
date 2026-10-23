package com.uagr.kmp.course.data.network.model.response.dashboard

import kotlinx.serialization.Serializable

@Serializable
data class TopExpenseCategoryResponse(
    val category_id: String? = null,
    val category_name: String? = null,
    val amount: String? = null,
    val percentage: String? = null,
)