package com.uagr.kmp.course.domain.usecase.register

enum class RegisterValidationError {
    NAME_EMPTY,
    EMAIL_EMPTY,
    EMAIL_INVALID,
    PASSWORD_EMPTY,
    CONFIRM_PASSWORD_EMPTY,
    PASSWORD_MISMATCH
}