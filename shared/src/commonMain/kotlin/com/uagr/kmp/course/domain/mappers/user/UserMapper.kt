/*
 * UserMapper.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.mappers.user

import com.uagr.kmp.course.data.local.model.user.UserEntity
import com.uagr.kmp.course.domain.model.user.UserModel

fun UserEntity.toDomain(): UserModel =
    UserModel(
        id = 0,
        email = email.orEmpty(),
        password = password.orEmpty(),
    )

fun UserModel.toEntity(): UserEntity =
    UserEntity(
        id = id,
        email = email,
        password = password,
    )
