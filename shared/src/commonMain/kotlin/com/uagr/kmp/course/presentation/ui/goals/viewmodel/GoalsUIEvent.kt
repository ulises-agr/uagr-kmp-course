package com.uagr.kmp.course.presentation.ui.goals.viewmodel

sealed class GoalsUiEvent {
    internal data object Idle : GoalsUiEvent()
    data object Success : GoalsUiEvent()
}
