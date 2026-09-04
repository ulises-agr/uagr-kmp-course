package com.uagr.kmp.course.feature.home.presentation


/**
 * Side effects
 *
 * Representa acciones efímeras que no deben persistir tras recomposiciones o rotaciones de pantalla (navegación, alertas, Snackbars).
 */
sealed interface HomeUiEffect {

    data object NavigateToHome : HomeUiEffect

}
