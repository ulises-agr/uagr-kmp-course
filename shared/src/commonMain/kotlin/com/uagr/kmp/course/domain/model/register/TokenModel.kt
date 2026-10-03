/*
// TokenModel.kt
// Created by Karla Judith Cervantes Higareda on 02/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.model.register

data class TokensModel(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String,
    val expiresIn: Int,
)