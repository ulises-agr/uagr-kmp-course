package com.uagr.kmp.course.data.network.model.response.register

import com.uagr.kmp.course.data.network.model.response.base.BaseResponse
import com.uagr.kmp.course.data.network.model.response.login.TokensResponse
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val user : UserResponse? = null,
    val tokens: TokensResponse? = null,
): BaseResponse()