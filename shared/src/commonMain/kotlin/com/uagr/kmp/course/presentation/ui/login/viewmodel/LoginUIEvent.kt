package com.uagr.kmp.course.presentation.ui.login.viewmodel

sealed class LoginUiEvent {
    internal data object Idle: LoginUiEvent()
    data object LoginSuccess: LoginUiEvent()
}
