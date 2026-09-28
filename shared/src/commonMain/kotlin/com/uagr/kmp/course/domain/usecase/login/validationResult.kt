/*
 * LoginValidationResult.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.login

sealed class loginValidationResult {
    data object EmailEmpty : loginValidationResult()
    data object EmailInvalid : loginValidationResult()
    data object PasswordEmpty : loginValidationResult()
    data object Success : loginValidationResult()
}
