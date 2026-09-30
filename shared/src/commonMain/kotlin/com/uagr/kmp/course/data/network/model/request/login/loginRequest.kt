package com.uagr.kmp.course.data.network.model.request.login

import kotlinx.serialization.Serializable

@Serializable
data class loginRequest(
    val email: String,
    val password: String,
    val device_id: String,
)