/*
 * BaseResponse.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.network.model.response.base

interface BaseResponse {
    val success: Boolean?
    val message: String?
}
