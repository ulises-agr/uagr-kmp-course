package com.uagr.kmp.course.domain.mapper.register

import com.uagr.kmp.course.data.local.model.register.UserEntity
import com.uagr.kmp.course.data.network.model.response.register.RegisterResponse
import com.uagr.kmp.course.domain.model.login.TokensModel
import com.uagr.kmp.course.domain.model.register.RegisterModel
import com.uagr.kmp.course.domain.model.register.UserModel

fun RegisterResponse.toDomain(): RegisterModel =
    RegisterModel(
        user = UserModel(
            id = user?.id.orEmpty(),
            name = user?.name.orEmpty(),
            email = user?.email.orEmpty(),
            currency = user?.email.orEmpty(),
            timezone = user?.timezone.orEmpty(),
            locale = user?.timezone.orEmpty(),
            is_active = user?.is_active ?: false,
            email_verified = user?.email_verified ?: false,
            created_at = user?.created_at.orEmpty(),
            updated_at = user?.updated_at.orEmpty()
        ),
        tokens = TokensModel(
            access_token = tokens?.access_token.orEmpty(),
            refresh_token = tokens?.refresh_token.orEmpty(),
            token_type = tokens?.token_type.orEmpty(),
            expires_in = tokens?.expires_in ?: 70000
        )
    )
fun UserEntity.toDomain(): UserModel =
    UserModel(
        id = id,
        name = name.orEmpty(),
        email = email.orEmpty(),
        currency = currency.orEmpty(),
        timezone = timezone.orEmpty(),
        locale = locale.orEmpty(),
        is_active = is_active ?: false,
        email_verified = email_verified ?: false,
        created_at = created_at.orEmpty(),
        updated_at = updated_at.orEmpty()
    )
fun UserModel.toEntity(): UserEntity =
    UserEntity(
        id = id,
        name = name,
        email = email,
        currency = currency,
        timezone = timezone,
        locale = locale,
        is_active = is_active,
        email_verified = email_verified ,
        created_at = created_at,
        updated_at = updated_at,
    )