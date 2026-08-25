/*
 * DashboardUiState.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.dashboard.viewmodel

import com.uagr.kmp.course.util.enums.StatusLoading

data class DashboardUiState(
    val isLoading: StatusLoading = StatusLoading.DISMISS_LOADING,
    val email: String = "",
)
