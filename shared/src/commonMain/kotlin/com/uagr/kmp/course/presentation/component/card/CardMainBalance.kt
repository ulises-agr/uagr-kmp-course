/*
// CardMainBalance.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.uagr.kmp.course.presentation.component.text.TextSmall
import com.uagr.kmp.course.presentation.component.text.TextSmallExtra
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.home_balance_total
import course.shared.generated.resources.home_income
import course.shared.generated.resources.home_expenses
import org.jetbrains.compose.resources.stringResource

@Composable
fun CardMainBalance(
    balanceText: String,
    balanceAmount: String,
    incomeText: String,
    incomeAmount: String,
    expensesText: String,
    expensesAmount: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = AppTheme.colors.backgrounds.blueBox,
    amountColor: Color = AppTheme.colors.text.white,
    labelColor: Color = AppTheme.colors.text.blueLight,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Dimens.corner24),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
    ) {
        Column(modifier = Modifier
            .padding(Dimens.padding24)
        ) {
            TextSmallExtra(
                text = balanceText,
                color = labelColor,
                textAlign = TextAlign.Start)
            Spacer(Modifier.height(Dimens.height8))
            TextBigBold(
                text = balanceAmount,
                modifier = Modifier
                    .fillMaxWidth(),
                color = amountColor,
                textAlign = TextAlign.Start,
            )
            Spacer(Modifier.height(Dimens.height24))
            Row(
                horizontalArrangement = Arrangement.spacedBy(Dimens.padding16)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(Dimens.padding4)
                ) {
                    TextSmallExtra(
                        text = incomeText,
                        color = labelColor,
                        textAlign = TextAlign.Start
                    )
                    TextSmall(
                        text = incomeAmount,
                        color = amountColor,
                        textAlign = TextAlign.Start
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(Dimens.padding4)
                ) {
                    TextSmallExtra(
                        text = expensesText,
                        color = labelColor,
                        textAlign = TextAlign.Start
                    )
                    TextSmall(
                        text = expensesAmount,
                        color = amountColor,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardMainBalancePreview() {
    SafeScreenContainerTest {
        CardMainBalance(
            modifier = Modifier.padding(Dimens.padding16),
            balanceText = stringResource(Res.string.home_balance_total),
            balanceAmount = homeDataMock.totalBalance,
            incomeText = stringResource(Res.string.home_income),
            incomeAmount = homeDataMock.incomeAmount,
            expensesText = stringResource(Res.string.home_expenses),
            expensesAmount = homeDataMock.expensesAmount,
        )
    }
}
