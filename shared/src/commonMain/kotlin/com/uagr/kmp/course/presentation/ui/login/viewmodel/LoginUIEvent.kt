package com.uagr.kmp.course.presentation.ui.login.viewmodel

sealed interface LoginUIEvent {

    data object LoginSuccess : LoginUIEvent

    data object InvalidCredentials : LoginUIEvent
}