package com.uagr.kmp.course.domain.model.register

import com.uagr.kmp.course.domain.model.login.TokensModel

data class RegisterModel(
    val user: UserModel,
    val tokens: TokensModel
)