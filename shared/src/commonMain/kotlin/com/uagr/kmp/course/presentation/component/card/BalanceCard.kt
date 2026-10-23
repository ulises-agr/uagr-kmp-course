/*
 * BalanceCard.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uagr.kmp.course.presentation.component.buton.ButtonCustom
import com.uagr.kmp.course.presentation.component.buton.CircularIconButtonCustom
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.component.text.TextMedium
import com.uagr.kmp.course.presentation.component.text.TextNormal
import com.uagr.kmp.course.presentation.component.text.TextNormalBold
import com.uagr.kmp.course.presentation.component.text.TextSmall
import com.uagr.kmp.course.presentation.component.text.TextSmallExtra
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.ic_info
import org.jetbrains.compose.resources.painterResource

@Composable
fun BalanceCard(
    modifierCard: Modifier = Modifier,
    modifierColum: Modifier = Modifier,
    title: String = "",
    totalBalance: String = "",
    subtitle: String = "",
    incomeTitle: String = "",
    incomeTotal: String = "",
    expensesTitle: String = "",
    expensesTotal: String = "",
) {
    SimpleCard(
        modifierCard = modifierCard,
        modifierColumn = modifierColum,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Dimens.height4),
            ) {
                TextSmall(
                    modifier = Modifier.fillMaxWidth(),
                    color = AppTheme.colors.text.lightSuperBlue,
                    text = title,
                    textAlign = TextAlign.Left,
                )
                TextBigBold(
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = Dimens.textSizeBigExtra,
                    color = AppTheme.colors.backgrounds.white,
                    text = totalBalance,
                    textAlign = TextAlign.Left,
                )
                TextSmall(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Gray,
                    text = subtitle,
                    textAlign = TextAlign.Left,
                )
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Column (
                        modifier = Modifier.weight(1f)
                    ){
                        TextSmall(
                            color = AppTheme.colors.text.lightSuperBlue,
                            text = incomeTitle,
                            textAlign = TextAlign.Left,
                        )
                        TextNormal(
                            color = AppTheme.colors.text.white,
                            text = incomeTotal,
                            textAlign = TextAlign.Left,
                        )
                    }
                    Column(
                        Modifier.weight(1f)
                    ){
                        TextSmall(
                            color = AppTheme.colors.text.lightSuperBlue,
                            text = expensesTitle,
                            textAlign = TextAlign.Left,
                        )
                        TextNormal(
                            color = AppTheme.colors.text.white,
                            text = expensesTotal,
                            textAlign = TextAlign.Left,
                        )
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PackagesCardPreview() {
    SafeScreenContainerTest {
        BalanceCard(
            modifierColum = Modifier.background(
                AppTheme.colors.primary
            ),
            title = "",
            totalBalance = "$--",
            subtitle = "Balance disponible",
            incomeTitle = "Ingresos",
            incomeTotal = "+ 18,5000",
            expensesTitle = "Gastos",
            expensesTotal = "-$8,460"
        )
    }
}

