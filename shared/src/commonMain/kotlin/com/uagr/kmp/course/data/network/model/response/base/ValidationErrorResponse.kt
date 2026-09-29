/*
 * ValidationErrorResponse.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.network.model.response.base

import kotlinx.serialization.Serializable

@Serializable
data class ValidationErrorResponse(
    val detail: List<ValidationErrorDetail>? = null,
)

@Serializable
data class ValidationErrorDetail(
    val msg: String? = null,
    val type: String? = null,
)
