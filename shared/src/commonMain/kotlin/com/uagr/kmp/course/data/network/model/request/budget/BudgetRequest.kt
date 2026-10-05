/*
 * BudgetRequest.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.model.request.budget

import kotlinx.serialization.Serializable

@Serializable
data class BudgetRequest(
    val query: String = "",
)
