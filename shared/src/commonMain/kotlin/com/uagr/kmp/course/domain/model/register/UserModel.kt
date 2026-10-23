package com.uagr.kmp.course.domain.model.register

data class UserModel(
    val id: String,
    val name: String,
    val email: String,
    val currency: String,
    val timezone: String,
    val locale: String,
    val is_active: Boolean,
    val email_verified: Boolean,
    val created_at: String,
    val updated_at: String,
)