package com.uagr.kmp.course.data.network.model.response.register

import kotlinx.serialization.Serializable

@Serializable
data class UserTokenResponse(
    val token_type: String?,
    val access_token: String?,
    val refresh_token: String?,
    val expires_in: Int?,
)