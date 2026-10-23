package com.uagr.kmp.course.domain.model.login

data class TokensModel(
    val access_token: String,
    val refresh_token: String,
    val token_type: String,
    val expires_in: Int,
)