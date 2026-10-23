package com.uagr.kmp.course.domain.usecase.register

class ValidateRegisterFormUseCase {
    operator fun invoke(
        name: String,
        email: String,
        password: String,
        passwordConfirm: String
    ): ValidateRegisterResult {
        if (name.isBlank()) {
            return ValidateRegisterResult.NameEmpty
        }
        if (email.isBlank()) {
            return ValidateRegisterResult.EmailEmpty
        }
        if (password.isBlank()) {
            return ValidateRegisterResult.PasswordEmpty
        }
        if (passwordConfirm.isBlank()) {
            return ValidateRegisterResult.PasswordConfirmEmpty
        }
        if (passwordConfirm != password) {
            return ValidateRegisterResult.IsNotSamePassword
        }

        return ValidateRegisterResult.Success
    }
}