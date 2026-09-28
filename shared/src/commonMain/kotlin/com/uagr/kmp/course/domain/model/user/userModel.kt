/*
 * userModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.model.user

data class userModel(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val role: String,
)
