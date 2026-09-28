/*
 * loginUiState.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.login.viewmodel

import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.utils.operators.StatusLoading

data class loginUiState(
    val isLoading: StatusLoading = StatusLoading.DISMISS_LOADING,
    val email: String = "",
    val password: String = "",
    val errorDialog: ErrorDialogModel? = null,
)