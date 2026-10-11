package com.uagr.kmp.course.domain.mapper.register

import com.uagr.kmp.course.data.network.model.response.register.RegisterResponse
import com.uagr.kmp.course.domain.model.register.RegisterModel

fun RegisterResponse.toDomain(): RegisterModel {
    return RegisterModel(
        accessToken = tokens?.access_token.orEmpty(),
        refreshToken = tokens?.refresh_token.orEmpty(),
        tokenType = tokens?.token_type.orEmpty(),
        expiresIn = tokens?.expires_in ?: 0
    )
}