/*
 * BudgetDataResponse.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.model.response.budget

import kotlinx.serialization.Serializable

@Serializable
data class BudgetDataResponse(
    val title: String? = null,
    val description: String? = null,
)
