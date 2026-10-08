package com.uagr.kmp.course.domain.model.login

data class LoginModel(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String,
    val expiresIn: Int
)