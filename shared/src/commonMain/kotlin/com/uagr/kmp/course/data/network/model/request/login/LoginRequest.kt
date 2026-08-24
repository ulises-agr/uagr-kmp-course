/*
 * LoginCredentialsRequest.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.network.model.request.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginCredentialsRequest(
    val email: String,
    val password: String,
)
