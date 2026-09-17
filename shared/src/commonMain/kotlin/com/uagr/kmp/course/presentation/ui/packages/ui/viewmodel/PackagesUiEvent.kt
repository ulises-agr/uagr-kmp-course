/*
 * PackagesUiEvent.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.packages.ui.viewmodel

sealed class PackagesUiEvent {
    internal data object Idle: PackagesUiEvent()
    internal data object ShowDialog: PackagesUiEvent()
}