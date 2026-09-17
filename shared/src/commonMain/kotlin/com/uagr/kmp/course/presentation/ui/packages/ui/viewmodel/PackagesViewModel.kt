/*
 * PackagesViewModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.packages.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PackagesViewModel : ViewModel() {
    private var _packagesUiState = MutableStateFlow(value = PackageUiState())
    val packagesUiState: StateFlow<PackageUiState> = _packagesUiState.asStateFlow()

    private var _packagesUiEvent = MutableStateFlow<PackagesUiEvent>(value = PackagesUiEvent.Idle)
    val packagesUiEvent: StateFlow<PackagesUiEvent> = _packagesUiEvent.asStateFlow()

    fun showDialog() = viewModelScope.launch {
        _packagesUiEvent.emit(value = PackagesUiEvent.ShowDialog)
    }

    fun hideDialog() = viewModelScope.launch {
        _packagesUiEvent.emit(value = PackagesUiEvent.Idle)
    }
}