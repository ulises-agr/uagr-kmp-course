package com.uagr.kmp.course.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.uagr.kmp.course.core.ui.theme.colors.AppColors
import com.uagr.kmp.course.core.ui.theme.colors.ColorsLight
import com.uagr.kmp.course.core.ui.theme.colors.DarkColorScheme
import com.uagr.kmp.course.core.ui.theme.colors.LightColorScheme


internal val LocalThemeIsDark = compositionLocalOf { true }
internal val LocalAppTypography = compositionLocalOf { AppTypography }
internal val LocalAppColors = compositionLocalOf { LightColorScheme }


@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme

    SetSystemBarsColor(
        statusBarColor = AppTheme.colors.primary,
        navigationBarColor = AppTheme.colors.primary,
        darkIcons = !isDarkTheme
    )

    CompositionLocalProvider(
        LocalThemeIsDark provides isDarkTheme,
        LocalAppColors provides colorScheme,
        LocalAppTypography provides AppTypography
    ) {
       // SystemAppearance(isDarkTheme)
        MaterialTheme(
            colorScheme = colorScheme.toMaterialColorScheme(), // Mapeo seguro,
            typography = MaterialTheme.typography,
            content = content
        )
    }
}

fun AppColors.toMaterialColorScheme(): ColorScheme {
    return if (this.isDark) {
        darkColorScheme(
            primary = this.primary,
            onPrimary = this.onPrimary,
            secondary = this.secondary,
            onSecondary = this.onSecondary,
            background = this.background,
            onBackground = this.onBackground,
            surface = this.surface,
            onSurface = this.onSurface,
            error = this.error,
            onError = this.onError,
            errorContainer = this.errorContainer,
            onErrorContainer = this.onErrorContainer
        )
    } else {
        lightColorScheme(
            primary = this.primary,
            onPrimary = this.onPrimary,
            secondary = this.secondary,
            onSecondary = this.onSecondary,
            background = this.background,
            onBackground = this.onBackground,
            surface = this.surface,
            onSurface = this.onSurface,
            error = this.error,
            onError = this.onError,
            errorContainer = this.errorContainer,
            onErrorContainer = this.onErrorContainer
        )
    }
}

object AppTheme {

    val colors: AppColors
        @Composable @ReadOnlyComposable get() =
        LocalAppColors.current
/*
    val typography: AppTypography
        @Composable @ReadOnlyComposable get() =
            LocalAppTypography.current
*/
}

@Composable
internal expect fun SetSystemBarsColor(
    statusBarColor: Color,
    navigationBarColor: Color,
    darkIcons: Boolean
)