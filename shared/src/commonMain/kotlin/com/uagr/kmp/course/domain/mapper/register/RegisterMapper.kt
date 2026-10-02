/*
 * RegisterMapper.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.mapper.register

import com.uagr.kmp.course.data.network.model.response.register.UserTokenResponse
import com.uagr.kmp.course.data.network.model.response.register.RegisterResponse
import com.uagr.kmp.course.data.network.model.response.register.RegisterUserResponse
import com.uagr.kmp.course.domain.model.register.RegisterDataModel
import com.uagr.kmp.course.domain.model.register.RegisterModel
import com.uagr.kmp.course.domain.model.user.UserModel
import com.uagr.kmp.course.domain.model.user.UserTokenModel

fun RegisterResponse.toDomain(): RegisterModel =
    RegisterModel(
        success = success ?: false,
        message = message.orEmpty(),
        data = RegisterDataModel(
            user = user?.toDomain(),
            tokens = tokens?.toDomain(),
        ),
    )

private fun RegisterUserResponse.toDomain(): UserModel =
    UserModel(
        id = id.orEmpty(),
        full_name = name.orEmpty(),
        email = email.orEmpty(),
        currency = currency.orEmpty(),
        timezone = timezone.orEmpty(),
        locale = locale.orEmpty(),
        is_active = is_active ?: false,
        email_verified = email_verified ?: false,
        created_at = created_at.orEmpty(),
        updated_at = updated_at.orEmpty(),
    )

private fun UserTokenResponse.toDomain(): UserTokenModel =
    UserTokenModel(
        token_type = token_type.orEmpty(),
        access_token = access_token.orEmpty(),
        refresh_token = refresh_token.orEmpty(),
        expires_in = expires_in ?: 0,
    )
