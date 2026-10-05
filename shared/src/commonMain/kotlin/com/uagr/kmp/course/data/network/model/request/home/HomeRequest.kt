/*
 * HomeRequest.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.model.request.home

import kotlinx.serialization.Serializable

@Serializable
data class HomeRequest(
    val query: String = "",
)
