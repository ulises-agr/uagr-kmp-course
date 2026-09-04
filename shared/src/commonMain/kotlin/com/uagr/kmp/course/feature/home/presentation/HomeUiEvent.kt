package com.uagr.kmp.course.feature.home.presentation

/**
 * Las Intenciones
 *
 * Es una sealed interface que define todas las acciones que el usuario puede realizar en la pantalla.
 */
sealed interface HomeUiEvent {
    data object OnNavigateToHome : HomeUiEvent
}