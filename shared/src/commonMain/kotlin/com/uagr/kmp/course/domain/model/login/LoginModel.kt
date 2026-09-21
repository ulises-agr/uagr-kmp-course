package com.uagr.kmp.course.domain.model.login

data class LoginModel(
    val success: Boolean,
    val message: String,
    val data: LoginDataModel?,
)

data class LoginDataModel(
    val user: UserModel?,
    val tokens: UserTokensModel?,
)

data class UserTokensModel(
    val token_type: String,
    val access_token: String,
    val refresh_token: String,
    val expires_in: Int,
)

data class UserModel(
    val id: String,
    val fullName : String,
    val email : String,
    val phone : String,
    val role : String,
    val status : String,
)