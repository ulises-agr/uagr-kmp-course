package com.uagr.kmp.course.data.network.model.request

import kotlinx.serialization.Serializable

/*
// RegisterRequest.kt
// Created by Karla Judith Cervantes Higareda on 29/09/26.
// Copyright (c) 2026. All rights reserved.
*/

@Serializable
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
)
