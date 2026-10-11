package com.uagr.kmp.course.data.network.model.response.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val access_token: String? = null,
    val refresh_token: String? = null,
    val token_type: String? = null,
    val expires_in: Int? = null
)