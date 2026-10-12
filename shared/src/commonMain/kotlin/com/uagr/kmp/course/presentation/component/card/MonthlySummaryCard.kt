package com.uagr.kmp.course.presentation.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun MonthlySummaryCard(
    title: String,
    amount: String,
    percentage: String,
    percentageColor: Color,
    modifier: Modifier = Modifier
) {
    SimpleCard(
        modifierCard = modifier,
        cardBackgroundColor = AppTheme.colors.backgrounds.white,
        cardElevation = Dimens.elevation2,
        paddingColumn = Dimens.padding24
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(Dimens.padding8)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = amount,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = percentage,
                color = percentageColor,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MonthlySummaryCardPreview() {
    SafeScreenContainerTest {
        MonthlySummaryCard(
            title = "Gastos",
            amount = "$8,460",
            percentage = "↓ 12%",
            percentageColor = AppTheme.colors.finTrackIncome
        )
    }
}