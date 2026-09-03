package com.uagr.kmp.course.feature.welcome.presentation

/**
 * Las Intenciones
 *
 * Es una sealed interface que define todas las acciones que el usuario puede realizar en la pantalla.
 */
sealed interface WelcomeUiEvent {
    data class OnAmountChanged(val newAmount: Double) : WelcomeUiEvent
    data object OnPaymentClick : WelcomeUiEvent
    data object OnDismissError : WelcomeUiEvent
    data object OnNavigateToHome : WelcomeUiEvent
}