package com.uagr.kmp.course.presentation.ui.register.viewmodel

sealed interface RegisterUIEvent {

    data object RegisterSuccess : RegisterUIEvent
}