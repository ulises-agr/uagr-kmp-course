package com.uagr.kmp.course.data.network.model.login.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
	val password: String?,
	val email: String?
)

