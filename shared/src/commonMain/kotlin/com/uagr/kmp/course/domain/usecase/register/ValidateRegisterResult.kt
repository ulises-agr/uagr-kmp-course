package com.uagr.kmp.course.domain.usecase.register

sealed class ValidateRegisterResult {
    data object NameEmpty: ValidateRegisterResult()
    data object EmailEmpty: ValidateRegisterResult()
    data object PasswordEmpty: ValidateRegisterResult()
    data object PasswordConfirmEmpty: ValidateRegisterResult()
    data object IsNotSamePassword: ValidateRegisterResult()
    data object Success: ValidateRegisterResult()
}