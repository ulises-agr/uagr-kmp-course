/*
// LoginCardExample.kt
// Created by Karla Judith Cervantes Higareda on 29/09/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.component.text.TextSmall
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.badge
import course.shared.generated.resources.balance
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginCard(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.height210),
        shape = RoundedCornerShape(Dimens.corner28),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.backgrounds.blue
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.padding28),
        ) {
            TextBigBold(
                text = stringResource(Res.string.badge),
                color = AppTheme.colors.text.white
            )
            TextSmall(
                text = stringResource(Res.string.balance),
                color = AppTheme.colors.text.blueLight
            )
            Spacer(Modifier.height(Dimens.height16))
            BalanceBars(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

@Composable
private fun BalanceBars(
    modifier: Modifier = Modifier,
) {
    val heights = listOf(
        18.dp,
        23.dp,
        28.dp,
        33.dp,
        38.dp,
        43.dp,
        48.dp
    )
    Row(
        modifier = modifier
            .background(
                color = AppTheme.colors.backgrounds.blueBox,
                shape = RoundedCornerShape(Dimens.corner18)
            )
            .padding(
                horizontal = Dimens.padding18,
                vertical = Dimens.padding8
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        heights.forEach { height ->
            Box(
                modifier = Modifier
                    .width(Dimens.width18)
                    .height(height)
                    .clip(
                        shape = RoundedCornerShape(Dimens.corner6))
                    .background(
                        color = AppTheme.colors.backgrounds.blueBar,
                        shape = RoundedCornerShape(Dimens.corner6)
                    ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginCardPreview() {
    SafeScreenContainerTest {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = Dimens.padding16),
            verticalArrangement = Arrangement.spacedBy(Dimens.padding16),
        ) {
            LoginCard(
            )
        }
    }
}