/*
 * LoginResponse.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.model.login

data class LoginDataModel(
    val success: Boolean,
    val message: String,
    val data: LoginModel?,
)
