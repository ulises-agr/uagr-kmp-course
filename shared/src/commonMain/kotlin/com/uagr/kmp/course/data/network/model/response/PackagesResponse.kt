/*
 * PackagesResponse.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.network.model.response

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
