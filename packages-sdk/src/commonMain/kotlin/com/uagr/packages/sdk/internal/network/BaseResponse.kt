/*
 * BaseResponse.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.internal.network

interface BaseResponse {
    val success: Boolean?
    val message: String?
}
