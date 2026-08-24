/*
 * DashboardUiEvent.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.dashboard.viewmodel

import org.jetbrains.compose.resources.StringResource

sealed class DashboardUiEvent {
    internal data object Idle : DashboardUiEvent()
    data class ShowErrorDialog(val message: StringResource) : DashboardUiEvent()
}
