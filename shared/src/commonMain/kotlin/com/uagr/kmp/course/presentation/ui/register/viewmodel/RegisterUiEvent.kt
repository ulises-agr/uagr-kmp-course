package com.uagr.kmp.course.presentation.ui.register.viewmodel

import com.kmp.presentation.welcome.viewmodel.LoginUiEvent

sealed class RegisterUiEvent {
    internal data object Idle : RegisterUiEvent()
    data object ShowVersionInfoDialog : RegisterUiEvent()
    data object RegisterSuccess: RegisterUiEvent()
}