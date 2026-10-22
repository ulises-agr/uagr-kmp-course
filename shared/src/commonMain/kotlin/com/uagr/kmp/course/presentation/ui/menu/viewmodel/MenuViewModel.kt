/*
// MenuViewModel.kt
// Created by Karla Judith Cervantes Higareda on 10/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.menu.viewmodel

import androidx.lifecycle.ViewModel
import com.uagr.kmp.course.presentation.component.card.HomeMenuOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class MenuViewModel : ViewModel() {
    private val _menuUiState = MutableStateFlow(MenuUiState())
    val menuUiState: StateFlow<MenuUiState> = _menuUiState.asStateFlow()

    private val _menuUiEvent = MutableStateFlow<MenuUiEvent>(MenuUiEvent.Idle)
    val menuUiEvent: StateFlow<MenuUiEvent> = _menuUiEvent.asStateFlow()

    fun updateActiveOption(option: HomeMenuOption) {
        _menuUiState.update { state -> state.copy(selectedOption = option) }
    }

    fun selectOption(option: HomeMenuOption) {
        if (option == _menuUiState.value.selectedOption) return
        _menuUiEvent.value = MenuUiEvent.Navigate(option)
    }

    fun clearEvent() {
        _menuUiEvent.value = MenuUiEvent.Idle
    }
}
