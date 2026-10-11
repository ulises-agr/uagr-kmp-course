package com.uagr.kmp.course.domain.model.register

data class RegisterModel(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String,
    val expiresIn: Int
)