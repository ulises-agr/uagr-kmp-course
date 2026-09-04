package com.uagr.kmp.course.core.ui.theme.colors

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color


/**
 * primary: buttons, links, selection, estados activos
 * secondary: chips de filtro o botones secundarios.
 * background: background of the app
 * background: El fondo base de la pantalla completa detrás de todo el contenido.
 * onBackground: iconos
 * surface: cards, bottom sheets, TopBar
 *
 * El prefijo on indica el color del texto/icono que se coloca encima del color base (ej. onPrimary va sobre primary).
 * Container son variantes de tono más suave pensadas para fondos de componentes (cards, chips, botones secundarios).
 *
 */
val LightColorScheme: AppColors
    get() = AppColors(
        primary = ColorsLight.Red,
        onPrimary = ColorsLight.White,
        secondary = ColorsLight.Red,
        onSecondary = ColorsLight.White,
        background = ColorsLight.White,
        onBackground = ColorsLight.Red,
        surface = ColorsLight.White,
        onSurface = ColorsLight.White,
        error = ColorsLight.Red,
        onError = ColorsLight.Red,
        errorContainer = ColorsLight.Red,
        onErrorContainer = ColorsLight.Red,
        isDark = false
    )

internal object ColorsLight {
    val Red = Color(0xFFC62828)
    val RedLight = Color(0xFFE66E6E) // RedButton Disabl
    val White = Color(0xFFFFFFFF)
    val Black = Color(0xFF000000)
    val grayHigh = Color(0x662D2D2D)
    val grayMedium = Color(0x66B9B9B9)
    val grayLight = Color(0xB3B9B9B9)
}
