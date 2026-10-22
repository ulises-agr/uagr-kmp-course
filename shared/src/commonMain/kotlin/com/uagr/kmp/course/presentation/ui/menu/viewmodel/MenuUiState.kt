/*
// MenuUiState.kt
// Created by Karla Judith Cervantes Higareda on 10/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.menu.viewmodel

import com.uagr.kmp.course.presentation.component.card.HomeMenuOption

data class MenuUiState(
    val selectedOption: HomeMenuOption = HomeMenuOption.HOME,
)
