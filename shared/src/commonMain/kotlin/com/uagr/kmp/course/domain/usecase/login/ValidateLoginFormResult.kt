package com.uagr.kmp.course.domain.usecase.login

sealed class ValidateLoginFormResult {
    data object EmptyFields : ValidateLoginFormResult()
    data object EmailEmpty : ValidateLoginFormResult()
    data object WrongEmailFormat : ValidateLoginFormResult()
    data object PasswordEmpty : ValidateLoginFormResult()
    data object Success : ValidateLoginFormResult()
}