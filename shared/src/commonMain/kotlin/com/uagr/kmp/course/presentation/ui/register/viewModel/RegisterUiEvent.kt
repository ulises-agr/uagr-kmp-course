/*
 * RegisterUiEvent.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.register.viewModel

sealed interface RegisterUiEvent {
    data object Idle : RegisterUiEvent
    data object RegisterSuccess : RegisterUiEvent
}