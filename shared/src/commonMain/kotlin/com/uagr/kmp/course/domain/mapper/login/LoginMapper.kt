package com.uagr.kmp.course.domain.mapper.bone

import com.uagr.kmp.course.data.network.model.response.login.LoginResponse
import com.uagr.kmp.course.domain.model.login.TokensModel

fun LoginResponse.toDomain(): TokensModel =
    TokensModel(
         access_token = access_token.orEmpty(),
         refresh_token = access_token.orEmpty(),
         token_type = token_type.orEmpty(),
         expires_in = expires_in ?: 0,
    )
