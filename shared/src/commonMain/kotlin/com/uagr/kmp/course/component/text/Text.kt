/*
 * Text.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.component.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.uagr.kmp.course.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.example
import org.jetbrains.compose.resources.stringResource

/**
 * Standardized typography system for the application.
 *
 * Usage guide:
 *
 * - `TextSmallExtra` (12px)
 *
 * - `TextSmall` (14px):
 *
 * - `TextNormal` (16px)
 *
 * - `TextMedium` (20px)
 *
 * - `TextBig` (24px)
 */

@Composable
fun TextSmallExtra(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = Dimens.textSizeExtraSmall,
    color: Color,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = fontSize,
            fontWeight = FontWeight.Normal,
        ),
        color = color,
    )
}

@Composable
fun TextSmallExtraBold(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = Dimens.textSizeExtraSmall,
    color: Color,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
        ),
        color = color,
    )
}

@Composable
fun TextSmall(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = Dimens.textSizeSmall,
    color: Color,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = fontSize,
            fontWeight = FontWeight.Normal,
        ),
        color = color,
    )
}

@Composable
fun TextSmallBold(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = Dimens.textSizeSmall,
    color: Color,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
        ),
        color = color,
    )
}

@Composable
fun TextNormal(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = Dimens.textSizeNormal,
    color: Color,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = fontSize,
            fontWeight = FontWeight.Normal,
        ),
        color = color,
    )
}

@Composable
fun TextNormalBold(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = Dimens.textSizeNormal,
    color: Color,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
        ),
        color = color,
    )
}

@Composable
fun TextMedium(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = Dimens.textSizeMedium,
    color: Color,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = fontSize,
            fontWeight = FontWeight.Normal,
        ),
        color = color,
    )
}

@Composable
fun TextMediumBold(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = Dimens.textSizeMedium,
    color: Color,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
        ),
        color = color,
    )
}

@Composable
fun TextBig(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = Dimens.textSizeBig,
    color: Color,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = fontSize,
            fontWeight = FontWeight.Normal,
        ),
        color = color,
    )
}

@Composable
fun TextBigBold(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = Dimens.textSizeBig,
    color: Color,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
        ),
        color = color,
    )
}

@Composable
fun TextUrlLink(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = Dimens.textSizeNormal,
    textColor: Color,
    linkColor: Color,
    text: String,
    linkText: String,
    url: String,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            pushStyle(
                SpanStyle(
                    color = textColor,
                    fontSize = fontSize,
                    fontWeight = FontWeight.Normal,
                )
            )
            append(text)
            withLink(
                LinkAnnotation.Url(
                    url = url,
                    styles = TextLinkStyles(
                        style = SpanStyle(
                            color = linkColor,
                            fontSize = fontSize,
                            fontWeight = FontWeight.Bold,
                        ),
                    ),
                )
            ) {
                append(linkText)
            }
        },
        style = MaterialTheme.typography.bodySmall.copy(textAlign = textAlign),
    )
}

@Preview(
    showBackground = true,
)
@Composable
private fun TextPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Dimens.padding16),
        verticalArrangement = Arrangement.spacedBy(Dimens.padding16),
    ) {
        // TextSmallExtra
        TextSmallExtra(
            color = Color.Black,
            text = stringResource(Res.string.example),
        )
        TextSmallExtraBold(
            color = Color.Black,
            text = stringResource(Res.string.example),
        )
        // TextSmall
        TextSmall(
            color = Color.Black,
            text = stringResource(Res.string.example),
        )
        TextSmallBold(
            color = Color.Black,
            text = stringResource(Res.string.example),
        )
        // TextNormal
        TextNormal(
            color = Color.Black,
            text = stringResource(Res.string.example),
        )
        TextNormalBold(
            color = Color.Black,
            text = stringResource(Res.string.example),
        )
        // TextMedium
        TextMedium(
            color = Color.Black,
            text = stringResource(Res.string.example),
        )
        TextMediumBold(
            color = Color.Black,
            text = stringResource(Res.string.example),
        )
        // TextBig
        TextBig(
            color = Color.Black,
            text = stringResource(Res.string.example),
        )
        TextBigBold(
            color = Color.Black,
            text = stringResource(Res.string.example),
        )
        // TextUrlLink
        TextUrlLink(
            textColor = Color.Black,
            linkColor = Color.Blue,
            text = stringResource(Res.string.example),
            linkText = stringResource(Res.string.example),
            url = stringResource(Res.string.example),
        )
    }
}
