/*
 * PackagesSdkTheme.kt
 * Copyright (c) 2026. All rights reserved
 *
 * Self-contained design tokens for the Packages SDK UI.
 * Intentionally independent from the host app design system.
 */
package com.uagr.packages.sdk.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class PackagesSdkColors(
    val primary: Color = Color(0xFF1565C0),
    val background: Color = Color(0xFFF5F5F5),
    val surface: Color = Color.White,
    val onSurface: Color = Color(0xFF212121),
    val muted: Color = Color(0xFF757575),
    val divider: Color = Color(0xFFE0E0E0),
    val loaderScrim: Color = Color(0x99000000),
    val onPrimary: Color = Color.White,
)

data class PackagesSdkDimens(
    val padding16: Dp = 16.dp,
    val height2: Dp = 2.dp,
    val height4: Dp = 4.dp,
    val height12: Dp = 12.dp,
    val height16: Dp = 16.dp,
    val height20: Dp = 20.dp,
    val height24: Dp = 24.dp,
    val height40: Dp = 40.dp,
    val elevation2: Dp = 2.dp,
    val corner20: Dp = 20.dp,
    val border2: Dp = 2.dp,
    val textExtraSmall: TextUnit = 12.sp,
    val textSmall: TextUnit = 14.sp,
    val textNormal: TextUnit = 16.sp,
    val textBigExtra: TextUnit = 40.sp,
)

private val LocalPackagesSdkColors = staticCompositionLocalOf { PackagesSdkColors() }
private val LocalPackagesSdkDimens = staticCompositionLocalOf { PackagesSdkDimens() }

object PackagesSdkTheme {
    val colors: PackagesSdkColors
        @Composable get() = LocalPackagesSdkColors.current

    val dimens: PackagesSdkDimens
        @Composable get() = LocalPackagesSdkDimens.current
}

@Composable
fun PackagesSdkTheme(
    colors: PackagesSdkColors = PackagesSdkColors(),
    dimens: PackagesSdkDimens = PackagesSdkDimens(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalPackagesSdkColors provides colors,
        LocalPackagesSdkDimens provides dimens,
        content = content,
    )
}
