package com.uagr.kmp.course.domain.mapper.register

import com.uagr.kmp.course.data.network.model.response.register.RegisterResponse
import com.uagr.kmp.course.domain.model.register.RegisterModel
import com.uagr.kmp.course.domain.model.user.userTokensModel

fun RegisterResponse.toDomain(): RegisterModel =
    RegisterModel(
        id = user.id,
        name = user.name,
        email = user.email,
        tokens = userTokensModel(
            access_token = tokens.accessToken,
            refresh_token = tokens.refreshToken,
            token_type = "bearer",
            expires_in = 0,
        ),
    )