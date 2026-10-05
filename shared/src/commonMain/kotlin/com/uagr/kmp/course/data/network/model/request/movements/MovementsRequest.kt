/*
 * MovementsRequest.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.model.request.movements

import kotlinx.serialization.Serializable

@Serializable
data class MovementsRequest(
    val query: String = "",
)
