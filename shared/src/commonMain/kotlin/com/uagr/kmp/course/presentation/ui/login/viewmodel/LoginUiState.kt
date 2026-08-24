/*
 * DashboardUiState.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.login.viewmodel

import com.uagr.kmp.course.utils.enums.StatusLoading

data class LoginUiState(
    val isLoading: StatusLoading = StatusLoading.DISMISS_LOADING,
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
)
