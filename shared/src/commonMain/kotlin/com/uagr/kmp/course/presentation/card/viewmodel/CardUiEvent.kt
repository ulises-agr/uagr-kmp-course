package com.kmp.presentation.welcome.viewmodel

sealed class CardUiEvent {
    internal data object Idle : CardUiEvent()
    data class ShowLoader(val show : Boolean) : CardUiEvent()
    data object OnClickIcon : CardUiEvent()
}