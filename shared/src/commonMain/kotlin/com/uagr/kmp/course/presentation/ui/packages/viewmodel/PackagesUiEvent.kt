/*
 * PackagesUiEvent.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.packages.viewmodel

sealed class PackagesUiEvent {
    internal data object Idle: PackagesUiEvent()
    internal data class ShowErrorDialog(val text: String): PackagesUiEvent()
}
