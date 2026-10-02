package com.uagr.kmp.course.domain.model.register

import com.uagr.kmp.course.domain.model.user.UserModel
import com.uagr.kmp.course.domain.model.user.UserTokenModel

data class RegisterDataModel(
    val user: UserModel?,
    val tokens: UserTokenModel?,
)
