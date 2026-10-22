/*
// MenuContainer.kt
// Created by Karla Judith Cervantes Higareda on 10/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.menu.viewmodel

import com.uagr.kmp.course.presentation.component.card.HomeMenuOption

sealed class MenuUiEvent {
    data object Idle : MenuUiEvent()
    data class Navigate(val option: HomeMenuOption) : MenuUiEvent()
}
