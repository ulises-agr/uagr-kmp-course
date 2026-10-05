/*
 * MovementsDataResponse.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.model.response.movements

import kotlinx.serialization.Serializable

@Serializable
data class MovementsDataResponse(
    val title: String? = null,
    val description: String? = null,
)
