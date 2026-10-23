package com.uagr.kmp.course.domain.usecase.login

class ValidateLoginFormUseCase {
    operator fun invoke(
        email: String,
        password: String,
    ): LoginValidateResult =
        when{
            email.isBlank() -> LoginValidateResult.EmailEmpty
            password.isBlank() -> LoginValidateResult.PasswordEmpty
            else -> LoginValidateResult.Success
        }
}