/*
 * ColorBackgrounds.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.theme.colors

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color

@Immutable
data class ColorBackgrounds(
    val black: Color = Color.Unspecified,
    val white: Color = Color.Unspecified,
    val canvas: Color = Color.Unspecified,
    val yellow: Color = Color.Unspecified,
    val blue: Color = Color.Unspecified,
    val blueBox: Color = Color.Unspecified,
    val blueBar: Color = Color.Unspecified,
    val blueMenuSelected: Color = Color.Unspecified,
    val grey: Color = Color.Unspecified,
)
