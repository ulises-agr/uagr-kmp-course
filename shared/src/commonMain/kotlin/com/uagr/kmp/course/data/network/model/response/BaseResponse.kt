/*
 * BaseResponse.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.network.model.response

open class BaseResponse(
    val success: Boolean? = false,
    val message: String? = "",
)
