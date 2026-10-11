package com.uagr.kmp.course.domain.mapper.login

import com.uagr.kmp.course.data.network.model.response.login.LoginResponse
import com.uagr.kmp.course.domain.model.login.LoginModel

fun LoginResponse.toDomain(): LoginModel {
    return LoginModel(
        accessToken = access_token.orEmpty(),
        refreshToken = refresh_token.orEmpty(),
        tokenType = token_type.orEmpty(),
        expiresIn = expires_in ?: 0
    )
}