/*
 * PaginationResponse.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.model.response.transaction

import kotlinx.serialization.Serializable

@Serializable
data class PaginationResponse(
    val page: Int? = null,
    val page_size: Int? = null,
    val total: Int? = null,
    val pages: Int? = null,
)
