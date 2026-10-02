/*
 * RegisterUserResponse.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.model.response.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserResponse(
    val id: String?,
    val name: String?,
    val email: String?,
    val currency: String?,
    val timezone: String?,
    val locale: String?,
    val is_active: Boolean?,
    val email_verified: Boolean?,
    val created_at: String?,
    val updated_at: String?,
)
