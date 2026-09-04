package com.uagr.kmp.course.feature.home.presentation


/**
 * El Estado
 *
 * Es una data class inmutable. Representa una "fotografía" exacta de todo lo que la pantalla necesita para dibujarse.
 * Contiene los datos necesarios para pintar la pantalla.
 */
data class HomeUiState(
  val isLoading: Boolean = false,
  val isSuccess: Boolean = false,
  val errorMessage: String? = null
)