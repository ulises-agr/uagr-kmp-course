/*
 * LoginRequest.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.model.request.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String,
    val device_id: String = "3fa85f64-5717-4562-b3fc-2c963f66afa6",
)
