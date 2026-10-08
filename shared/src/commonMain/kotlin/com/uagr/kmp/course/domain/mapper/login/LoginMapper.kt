package com.uagr.kmp.course.domain.mapper.login


import com.uagr.kmp.course.data.network.model.response.login.LoginResponse
import com.uagr.kmp.course.domain.model.login.LoginModel

fun LoginResponse.toDomain(): LoginModel {
    return LoginModel(
        accessToken = accessToken ?: "",
        refreshToken = refreshToken ?: "",
        tokenType = tokenType ?: "",
        expiresIn = expiresIn ?: 0
    )
}