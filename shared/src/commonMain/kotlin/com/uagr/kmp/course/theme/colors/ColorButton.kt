/*
 * ColorTexts.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.theme.colors

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ColorButton(
    val enable: Color = Color.Unspecified,
    val disable: Color = Color.Unspecified,
    val contentEnabled: Color = Color.Unspecified,
    val contentDisabled: Color = Color.Unspecified,
)
