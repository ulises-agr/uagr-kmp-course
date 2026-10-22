/*
// HomeUiEvent.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.home.viewmodel

import org.jetbrains.compose.resources.StringResource

sealed class HomeUiEvent {
    data object Idle : HomeUiEvent()
    data class ShowErrorDialog(val message: StringResource) : HomeUiEvent()
    data class ShowErrorStringDialog(val message: String) : HomeUiEvent()
}
