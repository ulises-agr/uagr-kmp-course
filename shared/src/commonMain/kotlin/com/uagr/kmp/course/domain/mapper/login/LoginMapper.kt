/*
 * LoginMapper.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.mapper.login

import com.uagr.kmp.course.data.network.model.response.login.LoginResponse
import com.uagr.kmp.course.domain.model.login.LoginDataModel
import com.uagr.kmp.course.domain.model.login.LoginModel
import com.uagr.kmp.course.domain.model.user.UserTokenModel

fun LoginResponse.toDomain(): LoginModel =
    LoginModel(
        success = success ?: false,
        message = message.orEmpty(),
        data = LoginDataModel(
            user = null,
            tokens = UserTokenModel(
                token_type = token_type.orEmpty(),
                access_token = access_token.orEmpty(),
                refresh_token = refresh_token.orEmpty(),
                expires_in = expires_in ?: 0,
            ),
        ),
    )
