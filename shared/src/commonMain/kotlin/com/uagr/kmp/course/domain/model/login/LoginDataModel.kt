package com.uagr.kmp.course.domain.model.login

import com.uagr.kmp.course.domain.model.user.UserModel
import com.uagr.kmp.course.domain.model.user.UserTokenModel

data class LoginDataModel(
    val user: UserModel?,
    val tokens: UserTokenModel?,
)