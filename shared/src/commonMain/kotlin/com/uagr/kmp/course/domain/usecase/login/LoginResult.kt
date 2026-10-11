package com.uagr.kmp.course.domain.usecase.login

sealed class LoginResult {

    data object Success : LoginResult()

    data object InvalidCredentials : LoginResult()
}