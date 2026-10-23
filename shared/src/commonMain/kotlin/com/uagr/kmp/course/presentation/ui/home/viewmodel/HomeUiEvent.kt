package com.uagr.kmp.course.presentation.ui.home.viewmodel

sealed class HomeUiEvent {
    internal data object Idle : HomeUiEvent()
    data class ShowVersionInfoDialog(val message: String) : HomeUiEvent()
}