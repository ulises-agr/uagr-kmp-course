/*
// LoginMapper.kt
// Created by Karla Judith Cervantes Higareda on 08/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.mapper.login

import com.uagr.kmp.course.data.network.model.response.login.LoginResponse
import com.uagr.kmp.course.domain.model.login.LoginModel

fun LoginResponse.toDomain(): LoginModel {
    val access = requireNotNull(accessToken) { "Missing access token" }
    require(access.isNotBlank()) { "Invalid access token" }
    return LoginModel(
        accessToken = access,
        refreshToken = refreshToken.orEmpty(),
        tokenType = tokenType.orEmpty(),
        expiresIn = expiresIn ?: 0,
    )
}
