/*
 * PackageUiState.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.packages.ui.viewmodel

import com.uagr.kmp.course.utils.operators.StatusLoading

data class PackageUiState(
    var isLoading: StatusLoading = StatusLoading.DISMISS_LOADING
)