package com.uagr.kmp.course.presentation.ui.home.viewmodel

sealed class HomeUiEvent {
    internal data object Idle : HomeUiEvent()
    data object Success : HomeUiEvent()
}
