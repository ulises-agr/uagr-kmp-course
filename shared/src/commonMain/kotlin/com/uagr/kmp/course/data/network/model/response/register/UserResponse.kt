package com.uagr.kmp.course.data.network.model.response.register

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String? = null,
    val name: String? = null,
    val email: String? = null,
    val currency: String? = null,
    val timezone: String? = null,
    val locale: String? = null,
    val is_active: Boolean? = null,
    val email_verified: Boolean? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
)