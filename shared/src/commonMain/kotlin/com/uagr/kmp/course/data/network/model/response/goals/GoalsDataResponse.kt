/*
 * GoalsDataResponse.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.model.response.goals

import kotlinx.serialization.Serializable

@Serializable
data class GoalsDataResponse(
    val title: String? = null,
    val description: String? = null,
)
