/*
 * loginUiEvent.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.login.viewmodel

sealed class loginUiEvent {
    internal data object idle: loginUiEvent()
    data object loginSuccess: loginUiEvent()
}