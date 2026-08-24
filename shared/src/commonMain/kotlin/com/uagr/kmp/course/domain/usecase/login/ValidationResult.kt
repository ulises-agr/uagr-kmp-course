/*
 * ValidationResult.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.login

sealed class ValidationResult {
    data object EmailEmpty : ValidationResult()
    data object PasswordEmpty : ValidationResult()
    data object ValidationSuccess : ValidationResult()
}
