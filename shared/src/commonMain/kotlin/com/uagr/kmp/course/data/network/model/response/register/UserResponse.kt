/*
// UserResponse.kt
// Created by Karla Judith Cervantes Higareda on 02/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.data.network.model.response.register

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
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




