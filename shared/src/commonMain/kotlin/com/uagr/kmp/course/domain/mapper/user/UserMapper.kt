/*
// UserModel.kt
// Created by Karla Judith Cervantes Higareda on 08/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.mapper.user

import com.uagr.kmp.course.data.local.model.user.UserEntity
import com.uagr.kmp.course.domain.model.register.UserModel

fun UserModel.toEntity(): UserEntity = UserEntity(
    id = id,
    name = name,
    email = email,
)
