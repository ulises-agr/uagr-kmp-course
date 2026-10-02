package com.uagr.kmp.course.domain.model.user

data class UserModel(
    val id: String = "",
    val full_name: String = "",
    val email: String = "",
    val phone: String = "",
    val role: String = "",
    val currency: String = "",
    val timezone: String = "",
    val locale: String = "",
    val is_active: Boolean = false,
    val email_verified: Boolean = false,
    val created_at: String = "",
    val updated_at: String = "",
)
