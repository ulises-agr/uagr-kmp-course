package com.uagr.kmp.course.domain.mapper.login

import com.uagr.kmp.course.data.network.model.login.response.LoginDataResponse
import com.uagr.kmp.course.data.network.model.login.response.LoginResponse
import com.uagr.kmp.course.data.network.model.login.response.UserResponse
import com.uagr.kmp.course.data.network.model.login.response.UserTokensResponse
import com.uagr.kmp.course.domain.model.login.LoginDataModel
import com.uagr.kmp.course.domain.model.login.LoginModel
import com.uagr.kmp.course.domain.model.login.UserModel
import com.uagr.kmp.course.domain.model.login.UserTokensModel


fun LoginResponse.toDomain() : LoginModel =
    LoginModel(
        success = success ?: false,
        message = message.toString(),
        data = data?.toDomain()
    )

fun LoginDataResponse.toDomain() : LoginDataModel =
    LoginDataModel(
        user = user?.toDomain(),
        tokens = tokens?.toDomain()
    )

fun UserTokensResponse.toDomain() : UserTokensModel =
    UserTokensModel(
        token_type = token_type.orEmpty(),
        access_token = access_token.orEmpty(),
        refresh_token = refresh_token.orEmpty(),
        expires_in = expires_in ?: 0,
    )

fun UserResponse.toDomain() : UserModel =
    UserModel(
        id = id.orEmpty(),
        full_name = full_name.orEmpty(),
        email = email.orEmpty(),
        phone = phone.orEmpty(),
        role = role.orEmpty(),
        status = status.orEmpty()
)

