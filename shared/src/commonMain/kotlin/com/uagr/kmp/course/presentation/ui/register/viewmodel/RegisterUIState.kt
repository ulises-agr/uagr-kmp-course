package com.uagr.kmp.course.presentation.ui.register.viewmodel

import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.utils.operators.StatusLoading

data class RegisterUiState(
    val isLoading: StatusLoading = StatusLoading.DISMISS_LOADING,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val passwordVisible: Boolean = false,
    val confirmPasswordVisible: Boolean = false,
    val errorDialog: ErrorDialogModel? = null,
)
