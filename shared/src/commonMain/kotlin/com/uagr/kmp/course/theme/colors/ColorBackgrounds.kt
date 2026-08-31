/*
 * ColorBackgrounds.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.theme.colors

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ColorBackgrounds(
    val backgroundColor : Color = Color.Unspecified,
    val transparentBlackBackground : Color = Color.Unspecified,
    val black: Color = Color.Unspecified,
    val white: Color = Color.Unspecified,
    val yellow: Color = Color.Unspecified,
)
