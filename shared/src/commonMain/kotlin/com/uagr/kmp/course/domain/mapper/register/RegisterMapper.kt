/*
// RegisterMapper.kt
// Created by Karla Judith Cervantes Higareda on 29/09/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.mapper.register

import com.uagr.kmp.course.data.network.model.response.register.RegisterResponse
import com.uagr.kmp.course.domain.model.register.RegisterModel
import com.uagr.kmp.course.domain.model.register.UserModel
import com.uagr.kmp.course.domain.model.register.TokensModel

fun RegisterResponse.toDomain(): RegisterModel = RegisterModel(
    user = UserModel(
        id = user?.id.orEmpty(),
        name = user?.name.orEmpty(),
        email = user?.email.orEmpty(),
    ),
    tokens = TokensModel(
        accessToken = tokens?.accessToken.orEmpty(),
        refreshToken = tokens?.refreshToken.orEmpty(),
        tokenType = tokens?.tokenType.orEmpty(),
        expiresIn = tokens?.expiresIn ?: 0,
    ),
)
