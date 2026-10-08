/*
// LoginModel.kt
// Created by Karla Judith Cervantes Higareda on 08/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.model.login

data class LoginModel(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String,
    val expiresIn: Int,
)