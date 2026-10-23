package com.uagr.kmp.course.data.network.model.response.transactions

import com.uagr.kmp.course.data.network.model.response.base.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class CategoriesResult(
    val id: String? = null,
    val user_id: String? = null,
    val name: String? = null,
    val type: String? = null,
    val icon: String? = null,
    val color: String? = null,
    val is_default: Boolean? = null,
    val created_at: String? = null,
)