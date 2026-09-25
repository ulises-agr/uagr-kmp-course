/*
 * LoginUiState.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.packages.viewmodel

import com.uagr.kmp.course.domain.model.packages.PackageDataModel
import com.uagr.kmp.course.domain.model.packages.PackageModel
import com.uagr.kmp.course.utils.operators.StatusLoading

data class PackageUiState(
    val showLoader: StatusLoading = StatusLoading.HIDE_LOADING,
    val showToast: Boolean = false,
    val packages: List<PackageDataModel> = emptyList(),
)


