package com.uagr.kmp.course.domain.usecase.login


sealed class LoginResult {

    data object Success : LoginResult()

    data object EmptyEmail : LoginResult()

    data object EmptyPassword : LoginResult()

    data object EmptyEmailAndPassword : LoginResult()

    data object InvalidCredentials : LoginResult()
}