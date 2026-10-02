package com.uagr.kmp.course.domain.usecase.register

sealed class RegisterValidationResult {
    data object EmailEmpty : RegisterValidationResult()
    data object PasswordEmpty : RegisterValidationResult()
    data object ConfirmPasswordEmpty : RegisterValidationResult()
    data object NameEmpty : RegisterValidationResult()
    data object DifferentPasswords : RegisterValidationResult()
    data object Success : RegisterValidationResult()
}
