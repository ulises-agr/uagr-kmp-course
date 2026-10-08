/*
 * loginModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.model.login

import com.uagr.kmp.course.domain.model.user.userTokensModel

data class LoginModel(
    val tokenType: String = "",
    val accessToken: String = "",
    val refreshToken: String = "",
    val expiresIn: Int = 0,
)
