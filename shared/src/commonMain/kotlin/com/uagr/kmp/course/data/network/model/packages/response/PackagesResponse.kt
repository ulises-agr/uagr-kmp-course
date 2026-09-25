/*
 * PackagesResponse.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.network.model.packages.response

import com.uagr.kmp.course.data.network.model.BaseResponse
import kotlinx.serialization.Serializable
@Serializable
data class PackagesResponse(
    val data: List<PackagesDataResponse>?,
) : BaseResponse()


