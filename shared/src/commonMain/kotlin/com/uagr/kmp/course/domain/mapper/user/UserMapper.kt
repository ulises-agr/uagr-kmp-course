package com.uagr.kmp.course.domain.mapper.user

import com.uagr.kmp.course.data.local.model.UserEntity
import com.uagr.kmp.course.domain.model.login.UserModel

fun UserModel.toEntity() : UserEntity =
    UserEntity(
        name = full_name,
        email = email,
        phone = phone,
        role = role,
        status = status,
    )

fun UserEntity.toDomain() : UserModel =
    UserModel(
        id = id.toString(),
        full_name = name.orEmpty(),
        email = email.orEmpty(),
        phone = phone.orEmpty(),
        role = role.orEmpty(),
        status = status.orEmpty(),
    )
