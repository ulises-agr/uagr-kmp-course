/*
 * PackagesUiEvent.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.presentation

sealed class PackagesUiEvent {
    data object Idle : PackagesUiEvent()
    data class ShowError(val message: String) : PackagesUiEvent()
}
