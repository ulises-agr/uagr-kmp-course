/*
 * RegisterResponse.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.network.model.response.register

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    @SerialName("user") val user: UserRegisterDto,
    @SerialName("tokens") val tokens: TokensRegisterDto
)

@Serializable
data class UserRegisterDto(
    @SerialName("id") val id: String,
    @SerialName("email") val email: String,
    @SerialName("name") val name: String
)

@Serializable
data class TokensRegisterDto(
    @SerialName("access_token") val accessToken: String,
    @SerialName("refresh_token") val refreshToken: String
)