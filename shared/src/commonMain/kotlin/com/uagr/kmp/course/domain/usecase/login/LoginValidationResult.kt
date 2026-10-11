package com.uagr.kmp.course.domain.usecase.login

sealed class LoginValidationResult {

    data object ValidationPassed : LoginValidationResult()

    data object EmptyEmail : LoginValidationResult()

    data object InvalidEmail : LoginValidationResult()

    data object EmptyPassword : LoginValidationResult()

    data object EmptyEmailAndPassword : LoginValidationResult()
}