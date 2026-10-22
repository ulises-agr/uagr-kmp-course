package com.uagr.kmp.course.presentation.ui.budget.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun BudgetContent() {

    val totalBudget = 12000
    val usedBudget = 8460

    data class BudgetCategory(
        val name: String,
        val used: Int,
        val limit: Int,
        val progressColor: Color
    )

    val categories = listOf(
        BudgetCategory(
            "Alimentación", 3200, 4000,
            AppTheme.colors.finTrackExpense
        ),
        BudgetCategory(
            "Transporte", 1850, 2500,
            AppTheme.colors.backgrounds.actionBlue
        ),
        BudgetCategory(
            "Servicios", 1199, 1500,
            AppTheme.colors.backgrounds.darkBlue
        ),
        BudgetCategory(
            "Entretenimiento", 620, 1500,
            AppTheme.colors.finTrackIncome
        )
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.padding24)
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.padding4)
        ) {
            Text(
                text = "Presupuestos",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Septiembre",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }


        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(Dimens.corner24),
            color = AppTheme.colors.backgrounds.white
        ) {
            Column(
                modifier = Modifier.padding(Dimens.padding16),
                verticalArrangement = Arrangement.spacedBy(Dimens.padding12)
            ) {
                Text(
                    text = "Presupuesto mensual",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "$12,000",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Usado $8,460 · 70%",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )

                LinearProgressIndicator(
                    progress = { usedBudget.toFloat() / totalBudget },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.height8),
                    color = AppTheme.colors.backgrounds.actionBlue,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    gapSize = Dimens.widthNome,
                    drawStopIndicator = {}
                )
            }
        }


        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.padding16)
        ) {
            Text(
                text = "Por categoría",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            categories.forEach { category ->

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(Dimens.corner20),
                    color = AppTheme.colors.backgrounds.white
                ) {
                    Column(
                        modifier = Modifier.padding(Dimens.padding16),
                        verticalArrangement = Arrangement.spacedBy(Dimens.padding8)
                    ) {
                        Text(
                            text = category.name,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "$${category.used} / $${category.limit}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        LinearProgressIndicator(
                            progress = {
                                category.used.toFloat() / category.limit
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(Dimens.height8),
                            color = category.progressColor,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                            gapSize = Dimens.widthNome,
                            drawStopIndicator = {}
                        )
                    }
                }
            }
        }
    }
}