/*
// LoginRequest.kt
// Created by Karla Judith Cervantes Higareda on 02/10/26.
// Copyright (c) 2026. All rights reserved.
*/

package com.uagr.kmp.course.data.network.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String,
    @SerialName("device_id") val deviceId: String,
) {
}