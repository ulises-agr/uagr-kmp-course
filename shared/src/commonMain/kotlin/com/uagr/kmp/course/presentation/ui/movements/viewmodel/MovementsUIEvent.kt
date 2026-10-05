package com.uagr.kmp.course.presentation.ui.movements.viewmodel

sealed class MovementsUiEvent {
    internal data object Idle : MovementsUiEvent()
    data object Success : MovementsUiEvent()
}
