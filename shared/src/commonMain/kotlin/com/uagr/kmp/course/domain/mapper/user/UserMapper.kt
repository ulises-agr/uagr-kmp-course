/*
 * UserMapper.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.mapper.user

import com.uagr.kmp.course.data.local.model.user.UserEntity
import com.uagr.kmp.course.domain.model.user.userModel

fun UserEntity.toDomain(): userModel =
    userModel(
        id = id.toString(),
        name = name.orEmpty(),
        email = email.orEmpty(),
        phone = phone.orEmpty(),
        role = role.orEmpty(),
    )

fun userModel.toEntity(): UserEntity =
    UserEntity(
        name = name,
        email = email,
        phone = phone,
        role = role,
    )
