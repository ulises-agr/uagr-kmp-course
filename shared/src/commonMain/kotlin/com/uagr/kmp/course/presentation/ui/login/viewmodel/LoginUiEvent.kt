/*
// LoginUiEvent.kt
// Created by Karla Judith Cervantes Higareda on 02/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.login.viewmodel

import org.jetbrains.compose.resources.StringResource

sealed class LoginUiEvent {
    internal data object Idle : LoginUiEvent()
    data object LoginSuccess : LoginUiEvent()
    internal data class ShowErrorDialog(val message: StringResource) : LoginUiEvent()
    internal data class ShowErrorStringDialog(val message: String) : LoginUiEvent()
}
