/*
 * WelcomeViewModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.kmp.presentation.welcome.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WelcomeViewModel: ViewModel() {

    private var _welcomeUiEvent = MutableStateFlow<WelcomeUiEvent>(WelcomeUiEvent.Idle)
    val welcomeUiEvent: StateFlow<WelcomeUiEvent> = _welcomeUiEvent.asStateFlow()

    fun showInfoDialog() = viewModelScope.launch {
        _welcomeUiEvent.emit(WelcomeUiEvent.ShowVersionInfoDialog)
    }

    fun resetUiEvent() = viewModelScope.launch {
        _welcomeUiEvent.emit(WelcomeUiEvent.Idle)
    }
}
