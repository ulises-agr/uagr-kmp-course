/*
 * PackagesResponse.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.data.network.model

import com.uagr.packages.sdk.internal.network.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class PackagesResponse(
    val data: List<PackagesDataResponse>?,
    override val success: Boolean? = false,
    override val message: String? = "",
) : BaseResponse
