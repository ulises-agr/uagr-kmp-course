package com.uagr.kmp.course.presentation.ui.register.viewmodel

sealed class RegisterUiEvent {
    internal data object Idle : RegisterUiEvent()
    data object RegisterSuccess : RegisterUiEvent()
}
