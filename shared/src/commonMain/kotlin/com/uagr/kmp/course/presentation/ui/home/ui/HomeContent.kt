package com.uagr.kmp.course.presentation.ui.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.uagr.kmp.course.presentation.component.card.BalanceSummaryCard
import com.uagr.kmp.course.presentation.component.card.MonthlySummaryCard
import com.uagr.kmp.course.presentation.component.movement.MovementItem
import com.uagr.kmp.course.presentation.component.navigation.HomeTab
import com.uagr.kmp.course.presentation.component.section.SectionHeader
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun HomeContent(
    onViewAllMovements: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.padding16)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.padding8)
        ) {
            Text(
                text = "Hola, Diego",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = "Tu panorama financiero",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
        }

        BalanceSummaryCard(
            balance = "$24,860.00",
            income = "+$18,500",
            expenses = "-$8,460"
        )

        Text(
            text = "Este mes",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.padding16)
        ) {
            MonthlySummaryCard(
                title = "Gastos",
                amount = "$8,460",
                percentage = "↓ 12%",
                percentageColor = AppTheme.colors.finTrackIncome,
                modifier = Modifier.weight(1f)
            )

            MonthlySummaryCard(
                title = "Ahorro",
                amount = "$3,120",
                percentage = "↑ 8%",
                percentageColor = AppTheme.colors.finTrackIncome,
                modifier = Modifier.weight(1f)
            )
        }

        SectionHeader(
            title = "Últimos movimientos",
            actionText = "Ver todos",
            onActionClick = onViewAllMovements
        )

        MovementItem(
            title = "Supermercado",
            description = "Hoy · Alimentación",
            amount = "-$860",
            color = AppTheme.colors.finTrackExpense,
            backgroundColor = AppTheme.colors.finTrackExpenseBackground,
            modifier = Modifier.fillMaxWidth()
        )

        MovementItem(
            title = "Nómina",
            description = "Ayer · Ingreso",
            amount = "+$15,500",
            color = AppTheme.colors.finTrackIncome,
            backgroundColor = AppTheme.colors.finTrackIncomeBackground,
            modifier = Modifier.fillMaxWidth()
        )

        MovementItem(
            title = "Internet",
            description = "18 sep · Servicios",
            amount = "-$599",
            color = AppTheme.colors.finTrackExpense,
            backgroundColor = AppTheme.colors.finTrackExpenseBackground,
            modifier = Modifier.fillMaxWidth()
        )
    }
}