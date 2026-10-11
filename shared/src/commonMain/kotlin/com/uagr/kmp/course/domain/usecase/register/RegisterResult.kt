package com.uagr.kmp.course.domain.usecase.register

sealed class RegisterResult {

    data object Success : RegisterResult()

    data object RegisterFailed : RegisterResult()
}