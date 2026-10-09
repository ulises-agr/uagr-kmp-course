/*
 * PaginationModel.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.model.transaction

data class PaginationModel(
    val page: Int = 0,
    val pageSize: Int = 0,
    val total: Int = 0,
    val pages: Int = 0,
)
