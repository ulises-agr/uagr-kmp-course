/*
 * WelcomeViewModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.kmp.presentation.welcome.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tarifchakder.ktoast.ToastDuration
import com.tarifchakder.ktoast.ToastState
import com.uagr.kmp.course.presentation.card.viewmodel.CardUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CardViewModel: ViewModel() {
    
    private var _cardUiEvent = MutableStateFlow<CardUiEvent>(CardUiEvent.Idle)
    val cardUiEvent: StateFlow<CardUiEvent> = _cardUiEvent.asStateFlow()
    
    private var _cardUiState = MutableStateFlow(CardUiState())
    val cardUiState: StateFlow<CardUiState> = _cardUiState.asStateFlow()
    
    fun onClickButton() = viewModelScope.launch{
        _cardUiState.update { state -> state.copy(showToast = false) }
        _cardUiState.update { state -> state.copy(showLoader = true) }
    }
    
    fun onClickIcon() = viewModelScope.launch{
        _cardUiState.update { state -> state.copy(showToast = true) }
    }
    
    fun showToast(toastState: ToastState) = viewModelScope.launch {
        toastState.showToast(message = "Card Clicked", duration = ToastDuration.Short)
    }
}
