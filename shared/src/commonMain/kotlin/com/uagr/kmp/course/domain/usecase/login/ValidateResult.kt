package com.uagr.kmp.course.domain.usecase.login

sealed class LoginValidateResult {
    data object EmailEmpty: LoginValidateResult()
    data object PasswordEmpty: LoginValidateResult()
    data object Success: LoginValidateResult()
}