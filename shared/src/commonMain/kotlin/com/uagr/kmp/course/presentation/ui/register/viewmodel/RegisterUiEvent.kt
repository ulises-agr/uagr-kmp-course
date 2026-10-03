/*
// RegisterUiEvent.kt
// Created by Karla Judith Cervantes Higareda on 01/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.register.viewmodel

import org.jetbrains.compose.resources.StringResource

sealed class RegisterUiEvent {
    internal data object Idle : RegisterUiEvent()
    data object Registered : RegisterUiEvent()
    internal data class ShowErrorDialog(val message: StringResource) : RegisterUiEvent()
    internal data class ShowErrorStringDialog(val message: String) : RegisterUiEvent()
}
