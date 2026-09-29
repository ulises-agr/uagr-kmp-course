package com.uagr.kmp.course.domain.model.user

data class UserTokenModel(
    val token_type: String,
    val access_token: String,
    val refresh_token: String,
    val expires_in: Int,
)