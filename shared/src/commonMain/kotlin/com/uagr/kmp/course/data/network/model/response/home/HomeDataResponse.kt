/*
 * HomeDataResponse.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.model.response.home

import kotlinx.serialization.Serializable

@Serializable
data class HomeDataResponse(
    val title: String? = null,
    val description: String? = null,
)
