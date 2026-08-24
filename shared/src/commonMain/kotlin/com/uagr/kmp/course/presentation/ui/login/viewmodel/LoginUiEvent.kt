/*
 * DashboardUiEvent.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.login.viewmodel

import org.jetbrains.compose.resources.StringResource

sealed class LoginUiEvent {
    internal data object Idle : LoginUiEvent()
    data class ShowErrorDialog(val message: StringResource) : LoginUiEvent()
    data object LoginSuccess : LoginUiEvent()
}
