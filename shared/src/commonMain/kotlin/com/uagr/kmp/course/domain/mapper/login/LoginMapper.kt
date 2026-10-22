/*
 * LoginMapper.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.mapper.login

import com.uagr.kmp.course.data.network.model.response.login.LoginResponse
import com.uagr.kmp.course.domain.model.login.LoginModel

fun LoginResponse.toDomain(): LoginModel =
    LoginModel(
        tokenType = token_type.orEmpty(),
        accessToken = access_token.orEmpty(),
        refreshToken = refresh_token.orEmpty(),
        expiresIn = expires_in ?: 0,
    )
