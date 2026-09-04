package com.uagr.kmp.course.core.ui.theme.colors

import androidx.compose.ui.graphics.Color

val DarkColorScheme: AppColors
    get() = AppColors(
        primary = ColorsDark.Red,
        onPrimary = ColorsDark.White,
        secondary = ColorsDark.Red,
        onSecondary = ColorsDark.White,
        background = ColorsDark.White,
        onBackground = ColorsDark.Red,
        surface = ColorsDark.White,
        onSurface = ColorsDark.White,
        error = ColorsDark.Red,
        onError = ColorsDark.Red,
        errorContainer = ColorsDark.Red,
        onErrorContainer = ColorsDark.Red,
        isDark = true
    )

internal object ColorsDark {
    val Red = Color(0xFFC62828)
    val RedLight = Color(0xFFE66E6E) // RedButton Disabl
    val White = Color(0xFFFFFFFF)
    val Black = Color(0xFF000000)
    val grayHigh = Color(0x662D2D2D)
    val grayMedium = Color(0x66B9B9B9)
    val grayLight = Color(0xB3B9B9B9)
}
