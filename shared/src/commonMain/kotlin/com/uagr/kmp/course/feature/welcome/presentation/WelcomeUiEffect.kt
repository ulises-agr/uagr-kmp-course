package com.uagr.kmp.course.feature.welcome.presentation


/**
 * Side effects
 *
 * Representa acciones efímeras que no deben persistir tras recomposiciones o rotaciones de pantalla (navegación, alertas, Snackbars).
 */
sealed interface WelcomeUiEffect {

    data class Payment(val dato: String) : WelcomeUiEffect
    data class ShowToast(val message: String) : WelcomeUiEffect
    data object NavigateToHome : WelcomeUiEffect

}
