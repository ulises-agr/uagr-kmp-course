/*
 * PackagesViewModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.packages.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PackagesViewModel: ViewModel() {

    private var _packagesUiState = MutableStateFlow(PackagesUiState())
    val packagesUiState: StateFlow<PackagesUiState> = _packagesUiState.asStateFlow()

    private var _packagesUiEvent = MutableStateFlow<PackagesUiEvent>(PackagesUiEvent.Idle)
    val packagesUiEvent: StateFlow<PackagesUiEvent> = _packagesUiEvent.asStateFlow()
}
