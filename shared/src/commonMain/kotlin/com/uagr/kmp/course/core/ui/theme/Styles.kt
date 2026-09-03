package com.uagr.kmp.course.core.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit


/**
 *  STYLES
 */

fun robotoLight(
    sp: TextUnit,
    color: Color,
    textDecoration: TextDecoration? = null
): TextStyle {
    return TextStyle(
        fontSize = sp,
        //fontFamily = robotoFontFamily,
        textDecoration = textDecoration,
        fontWeight = FontWeight.Light,
        color = color
    )
}

fun robotoNormal(
    sp: TextUnit,
    color: Color,
    textDecoration: TextDecoration? = null
): TextStyle {
    return TextStyle(
        fontSize = sp,
        //fontFamily = robotoFontFamily,
        textDecoration = textDecoration,
        fontWeight = FontWeight.Normal,
        color = color
    )
}

fun robotoMedium(
    sp: TextUnit,
    color: Color,
    textDecoration: TextDecoration? = null
): TextStyle {
    return TextStyle(
        fontSize = sp,
        //fontFamily = robotoFontFamily,
        textDecoration = textDecoration,
        fontWeight = FontWeight.Medium,
        color = color
    )
}

fun robotoBold(
    sp: TextUnit,
    color: Color,
    textDecoration: TextDecoration? = null
): TextStyle {
    return TextStyle(
        fontWeight = FontWeight.Bold,
        //fontFamily = robotoFontFamily,
        textDecoration = textDecoration,
        fontSize = sp,
        color = color
    )
}