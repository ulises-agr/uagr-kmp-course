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
data class UserResponse(
	val id: String?,
	val full_name: String?,
	val email: String?,
	val phone: String?,
	val role: String?,
	val status: String?,
	val created_at: String?,
	val updated_at: String?,
)

@Serializable
data class UserTokensResponse(
	val access_token: String?,
	val refresh_token: String?,
	val token_type: String?,
	val expiresIn: Int?
)


