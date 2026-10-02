package com.uagr.kmp.course.domain.model.register

/*
// RegisterModel.kt
// Created by Karla Judith Cervantes Higareda on 29/09/26.
// Copyright (c) 2026. All rights reserved.
*/

data class RegisterModel(
    val user: RegisteredUserModel?,
    val tokens: RegisterTokensModel?,
)

data class RegisteredUserModel(
    val id: String,
    val name: String,
    val email: String,
)

data class RegisterTokensModel(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String,
    val expiresIn: Long,
)
