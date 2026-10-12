package com.uagr.kmp.course.presentation.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun BalanceSummaryCard(
    balance: String,
    income: String,
    expenses: String
) {
    BlueCard {
        Text(
            text = "Balance total",
            color = AppTheme.colors.text.white,
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(modifier = Modifier.height(Dimens.height8))

        Text(
            text = balance,
            color = AppTheme.colors.text.white,
            fontSize = Dimens.textSizeLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(Dimens.height24))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Dimens.padding16)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Dimens.padding4)
            ) {
                Text(
                    text = "Ingresos",
                    color = AppTheme.colors.text.white,
                    style = MaterialTheme.typography.labelMedium
                )

                Text(
                    text = income,
                    color = AppTheme.colors.text.white,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Dimens.padding4)
            ) {
                Text(
                    text = "Gastos",
                    color = AppTheme.colors.text.white,
                    style = MaterialTheme.typography.labelMedium
                )

                Text(
                    text = expenses,
                    color = AppTheme.colors.text.white,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}