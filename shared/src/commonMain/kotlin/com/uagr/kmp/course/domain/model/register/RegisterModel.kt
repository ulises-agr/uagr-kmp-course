package com.uagr.kmp.course.domain.model.register

import com.uagr.kmp.course.domain.model.user.userTokensModel

data class RegisterModel(
    val id: String? = null,
    val name: String? = null,
    val email: String? = null,
    val tokens: userTokensModel? = null,
)