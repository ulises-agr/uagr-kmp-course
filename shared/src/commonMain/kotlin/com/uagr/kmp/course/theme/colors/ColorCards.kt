/*
 * ColorTexts.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.theme.colors

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ColorCards(
    val cardsTitle: Color = Color.Unspecified,
    val cardsSubtitle: Color = Color.Unspecified,
    val cardDivider: Color = Color.Unspecified,
)
