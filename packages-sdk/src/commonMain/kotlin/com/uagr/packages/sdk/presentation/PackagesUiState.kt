/*
 * PackagesUiState.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.presentation

import com.uagr.packages.sdk.domain.model.PackagesDataModel

data class PackagesUiState(
    val isLoading: PackagesLoadingStatus = PackagesLoadingStatus.DISMISS_LOADING,
    val packages: List<PackagesDataModel> = emptyList(),
)
