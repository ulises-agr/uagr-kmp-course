/*
 * PackagesSdkComponents.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.uagr.packages.sdk.presentation.PackagesLoadingStatus
import com.uagr.packages.sdk.ui.theme.PackagesSdkTheme

@Composable
internal fun PackagesSdkCard(
    onClick: () -> Unit = {},
    nameText: String,
    typeText: String,
    quantityText: String,
    unit: String,
    renewalText: String,
    buttonText: String,
) {
    val colors = PackagesSdkTheme.colors
    val dimens = PackagesSdkTheme.dimens

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = colors.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = dimens.elevation2),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimens.padding16),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(dimens.height4),
                ) {
                    SdkText(
                        text = nameText,
                        color = colors.onSurface,
                        fontSize = dimens.textNormal,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left,
                    )
                    SdkText(
                        text = typeText,
                        color = colors.muted,
                        fontSize = dimens.textSmall,
                        textAlign = TextAlign.Left,
                    )
                }
                Box(
                    modifier = Modifier
                        .size(dimens.height24)
                        .clip(CircleShape)
                        .border(
                            width = dimens.border2,
                            color = colors.primary,
                            shape = CircleShape,
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "i",
                        color = colors.primary,
                        fontSize = dimens.textSmall,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            Spacer(modifier = Modifier.height(dimens.height20))

            SdkText(
                text = quantityText,
                color = colors.onSurface,
                fontSize = dimens.textBigExtra,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            SdkText(
                text = unit,
                color = colors.onSurface,
                fontSize = dimens.textSmall,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(dimens.height16))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = dimens.height2,
                color = colors.divider,
            )

            Spacer(modifier = Modifier.height(dimens.height12))

            SdkText(
                text = renewalText,
                color = colors.muted,
                fontSize = dimens.textExtraSmall,
                textAlign = TextAlign.Left,
            )

            Spacer(modifier = Modifier.height(dimens.height20))

            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimens.height40),
                shape = RoundedCornerShape(size = dimens.corner20),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.primary,
                    contentColor = colors.onPrimary,
                ),
            ) {
                SdkText(
                    text = buttonText,
                    color = colors.onPrimary,
                    fontSize = dimens.textNormal,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
internal fun PackagesSdkLoader(isLoading: PackagesLoadingStatus) {
    if (isLoading != PackagesLoadingStatus.SHOW_LOADING) return

    val colors = PackagesSdkTheme.colors
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.loaderScrim),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(color = colors.primary)
    }
}

@Composable
internal fun PackagesSdkScreenScaffold(
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = PackagesSdkTheme.colors
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.background),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            content = content,
        )
    }
}

@Composable
private fun SdkText(
    text: String,
    color: Color,
    fontSize: TextUnit,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        textAlign = textAlign,
    )
}
