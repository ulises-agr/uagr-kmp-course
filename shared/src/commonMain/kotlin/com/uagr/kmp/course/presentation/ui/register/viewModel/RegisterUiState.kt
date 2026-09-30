/*
 * RegisterUiState.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.register.viewModel

import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.utils.operators.StatusLoading

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: StatusLoading = StatusLoading.DISMISS_LOADING,
    val errorDialog: ErrorDialogModel? = null
)