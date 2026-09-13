package com.uagr.kmp.course.data.network.model.response.packages

import com.uagr.kmp.course.data.network.model.response.base.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class PackagesResponse(
    val title: String?,
    val packageDescription: String?,
    val usedAmount: Double?,
    val unit: String?,
    val renewalText: String?,
    val buttonText: String?,
    val hasInfoIcon: Boolean?,
) : BaseResponse()