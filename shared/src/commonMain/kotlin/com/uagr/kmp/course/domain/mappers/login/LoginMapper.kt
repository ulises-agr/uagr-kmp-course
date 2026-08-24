/*
 * LoginMapper.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.mappers.login

import com.uagr.kmp.course.data.network.model.response.login.LoginDataResponse
import com.uagr.kmp.course.data.network.model.request.login.LoginCredentialsRequest
import com.uagr.kmp.course.data.network.model.response.login.LoginResponse
import com.uagr.kmp.course.data.network.model.response.login.LoginTokensResponse
import com.uagr.kmp.course.domain.model.login.LoginCredentialsModel
import com.uagr.kmp.course.domain.model.login.LoginDataModel
import com.uagr.kmp.course.domain.model.login.LoginModel
import com.uagr.kmp.course.domain.model.login.LoginTokensModel

fun LoginCredentialsModel.toRequest(): LoginCredentialsRequest =
    LoginCredentialsRequest(
        email = email,
        password = password,
    )

fun LoginDataResponse.toDomain(): LoginDataModel =
    LoginDataModel(
        success = success ?: false,
        message = message.orEmpty(),
        data = data?.toDomain(),
    )

fun LoginResponse.toDomain(): LoginModel =
    LoginModel(
        tokens = tokens?.toDomain(),
    )

fun LoginTokensResponse.toDomain(): LoginTokensModel =
    LoginTokensModel(
        tokenType = token_type.orEmpty(),
        accessToken = access_token.orEmpty(),
        refreshToken = refresh_token.orEmpty(),
        expiresIn = expires_in ?: 0,
    )
