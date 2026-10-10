/*
// CardTypeBalance.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.mock.homeDataMock
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.component.text.TextSmallExtra
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.home_expenses
import course.shared.generated.resources.home_change_down
import course.shared.generated.resources.home_change_up
import org.jetbrains.compose.resources.stringResource

@Composable
fun CardTypeBalance(
    title: String,
    amount: String,
    changeText: String,
    isIncreasing: Boolean,
    isPositiveChange: Boolean,
    modifier: Modifier = Modifier,
    positiveColor: Color = AppTheme.colors.status.success,
    negativeColor: Color = AppTheme.colors.status.error,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(Dimens.corner12),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.backgrounds.white),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(Dimens.padding16),
            verticalArrangement = Arrangement.spacedBy(Dimens.padding4),
        ) {
            TextSmallExtra(
                text = title,
                color = AppTheme.colors.text.gray,
                textAlign = TextAlign.Start,
            )
            TextBigBold(
                text = amount,
                modifier = Modifier.fillMaxWidth(),
                color = AppTheme.colors.text.black,
                textAlign = TextAlign.Start,
            )
            TextSmallExtra(
                text = stringResource(
                    if (isIncreasing) Res.string.home_change_up else Res.string.home_change_down,
                    changeText,
                ),
                color = if (isPositiveChange) positiveColor else negativeColor,
                textAlign = TextAlign.Start,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardTypeBalancePreview() {
    SafeScreenContainerTest {
        CardTypeBalance(
            modifier = Modifier.padding(Dimens.padding16),
            title = stringResource(Res.string.home_expenses),
            amount = homeDataMock.monthlyExpensesAmount,
            changeText = homeDataMock.expensesChange,
            isIncreasing = homeDataMock.expensesIncreasing,
            isPositiveChange = homeDataMock.expensesPositiveChange,
        )
    }
}
