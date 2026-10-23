package com.uagr.kmp.course.data.network.model.response.budget

import com.uagr.kmp.course.data.network.model.response.base.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class BudgetsResponse(
    val budget: String? = null,
    val spent: String? = null,
    val remaining: String? = null,
    val percentage: String? = null,
): BaseResponse()