/*
 * LoginUiState.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.packages.viewmodel

import com.uagr.kmp.course.domain.model.PackageModel

data class PackageUiState(
    val showLoader: Boolean = false,
    val showToast: Boolean = false,
    val packages: PackageModel? = null,
)


