/*
 * GoalsRequest.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.model.request.goals

import kotlinx.serialization.Serializable

@Serializable
data class GoalsRequest(
    val query: String = "",
)
