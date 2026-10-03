/*
// RegisterResponse.kt
// Created by Karla Judith Cervantes Higareda on 29/09/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.data.network.model.response.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val user: UserResponse?,
    val tokens: TokensResponse?,
)
