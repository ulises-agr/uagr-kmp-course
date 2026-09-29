/*
 * LoginMapper.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.mapper.login

import com.uagr.kmp.course.data.network.model.response.login.loginResponse
import com.uagr.kmp.course.domain.model.login.loginModel
import com.uagr.kmp.course.domain.model.user.userTokensModel

fun loginResponse.toDomain(): loginModel =
    loginModel(
        tokens = userTokensModel(
            token_type = token_type.orEmpty(),
            access_token = access_token.orEmpty(),
            refresh_token = refresh_token.orEmpty(),
            expires_in = expires_in ?: 0,
        ),
    )
