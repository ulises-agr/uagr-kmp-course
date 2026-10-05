/*
 * MovementsResponse.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.model.response.movements

import com.uagr.kmp.course.data.network.model.response.base.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class MovementsResponse(
    val data: MovementsDataResponse? = null,
) : BaseResponse()
