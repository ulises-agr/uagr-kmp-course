package com.uagr.kmp.course.data.network.model.response.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val tokens: RegisterTokens? = null
)

@Serializable
data class RegisterTokens(
    val access_token: String? = null,
    val refresh_token: String? = null,
    val token_type: String? = null,
    val expires_in: Int? = null
)