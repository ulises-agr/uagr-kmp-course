package com.uagr.kmp.course.domain.model.register

data class RegisterModel(
    val success: Boolean,
    val message: String,
    val data: RegisterDataModel?,
)
