package com.kmp.presentation.welcome.viewmodel

sealed class LoginUiEvent {
    internal data object Idle : LoginUiEvent()
    data class ShowVersionInfoDialog(val message: String) : LoginUiEvent()
    data object LoginSuccess: LoginUiEvent()
}