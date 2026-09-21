package com.uagr.kmp.course.presentation.login.viewmodel

sealed class LoginUIEvent {
    internal data object Idle : LoginUIEvent()
    data object LoginSuccess : LoginUIEvent()
}