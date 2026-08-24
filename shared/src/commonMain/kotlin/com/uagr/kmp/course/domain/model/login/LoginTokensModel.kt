/*
 * LoginTokensResponse.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.model.login

data class LoginTokensModel(
    val tokenType: String,
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Int,
)
