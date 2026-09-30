/*
 * RegisterRequest.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.network.model.request.register

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
    @SerialName("password") val password: String
)