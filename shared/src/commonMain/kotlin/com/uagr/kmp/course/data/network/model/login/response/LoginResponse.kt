package com.uagr.kmp.course.data.network.model.login.response

import com.uagr.kmp.course.data.network.model.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
	val data: LoginDataResponse?,
) : BaseResponse()

@Serializable
data class LoginDataResponse(
	val tokens: UserTokensResponse?,
	val user: UserResponse?
)

@Serializable
data class UserTokensResponse(
	val access_token: String?,
	val refresh_token: String?,
	val token_type: String?,
	val expires_in: Int?
)

@Serializable
data class UserResponse(
	val created_at: String?,
	val email: String?,
	val full_name: String?,
	val id: String?,
	val phone: String?,
	val role: String?,
	val status: String?,
	val updated_at: String?,
)

