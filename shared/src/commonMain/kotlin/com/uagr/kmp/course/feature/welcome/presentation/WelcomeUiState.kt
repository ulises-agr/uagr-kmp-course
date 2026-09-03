package com.uagr.kmp.course.feature.welcome.presentation


/**
 * El Estado
 *
 * Es una data class inmutable. Representa una "fotografía" exacta de todo lo que la pantalla necesita para dibujarse.
 * Contiene los datos necesarios para pintar la pantalla.
 */
data class WelcomeUiState(
  val totalAmount: Double = 0.0,
  val isLoading: Boolean = false,
  val isSuccess: Boolean = false,
  val errorMessage: String? = null
)