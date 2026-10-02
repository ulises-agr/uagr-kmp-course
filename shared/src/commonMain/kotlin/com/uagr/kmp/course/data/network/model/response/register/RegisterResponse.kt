package com.uagr.kmp.course.data.network.model.response.register

import com.uagr.kmp.course.data.network.model.response.base.BaseResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
// RegisterResponse.kt
// Created by Karla Judith Cervantes Higareda on 29/09/26.
// Copyright (c) 2026. All rights reserved.
*/

@Serializable
data class RegisterResponse(
    val user: RegisterUserResponse?,
    val tokens: RegisterTokensResponse?,
) : BaseResponse()

@Serializable
data class RegisterUserResponse(
    val id: String?,
    val name: String?,
    val email: String?,
    val currency: String?,
    val timezone: String?,
    val locale: String?,
    @SerialName("is_active") val isActive: Boolean?,
    @SerialName("email_verified") val emailVerified: Boolean?,
    @SerialName("created_at") val createdAt: String?,
    @SerialName("updated_at") val updatedAt: String?,
)

@Serializable
data class RegisterTokensResponse(
    @SerialName("access_token") val accessToken: String?,
    @SerialName("refresh_token") val refreshToken: String?,
    @SerialName("token_type") val tokenType: String?,
    @SerialName("expires_in") val expiresIn: Long?,
)
