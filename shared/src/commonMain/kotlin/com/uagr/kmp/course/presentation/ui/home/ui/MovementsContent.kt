package com.uagr.kmp.course.presentation.ui.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.uagr.kmp.course.presentation.component.movement.MovementItem
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun MovementsContent() {

    var selectedFilter by remember { mutableStateOf("Todos") }
    var searchText by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.padding24)
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.padding4)
        ) {
            Text(
                text = "Movimientos",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Septiembre 2026",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.padding12)
        ) {
            listOf("Todos", "Ingresos", "Gastos").forEach { filter ->

                val isSelected = selectedFilter == filter

                val backgroundColor = when (filter) {
                    "Todos" -> AppTheme.colors.backgrounds.actionBlue
                    "Ingresos" -> AppTheme.colors.finTrackIncomeBackground
                    else -> AppTheme.colors.finTrackExpenseBackground
                }

                val textColor = when (filter) {
                    "Todos" -> AppTheme.colors.text.white
                    "Ingresos" -> AppTheme.colors.finTrackIncome
                    else -> AppTheme.colors.finTrackExpense
                }

                Box(
                    modifier = Modifier
                        .background(
                            color = if (isSelected && filter == "Todos") {
                                backgroundColor
                            } else if (isSelected) {
                                backgroundColor
                            } else {
                                backgroundColor.copy(alpha = 0.5f)
                            },
                            shape = RoundedCornerShape(Dimens.corner24)
                        )
                        .clickable { selectedFilter = filter }
                        .padding(
                            horizontal = Dimens.padding16,
                            vertical = Dimens.padding12
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = filter,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (filter == "Todos" && !isSelected) {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        } else {
                            textColor
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Buscar movimiento...",
                    style = MaterialTheme.typography.bodySmall
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar"
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(Dimens.corner16),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                focusedContainerColor = AppTheme.colors.backgrounds.white,
                unfocusedContainerColor = AppTheme.colors.backgrounds.white,
                focusedBorderColor = AppTheme.colors.backgrounds.actionBlue,
                unfocusedBorderColor = Color.Transparent
            )
        )

        data class Movement(
            val title: String,
            val category: String,
            val amount: String,
            val isIncome: Boolean
        )

        val groups = listOf(
            "Hoy" to listOf(
                Movement("Supermercado", "Alimentación", "-$860", false),
                Movement("Café", "Comida", "-$95", false)
            ),
            "Ayer" to listOf(
                Movement("Nómina", "Trabajo", "+$15,500", true),
                Movement("Gasolina", "Transporte", "-$1,100", false)
            ),
            "18 septiembre" to listOf(
                Movement("Internet", "Servicios", "-$599", false)
            )
        )

        groups.forEach { (date, movements) ->

            val filteredMovements = movements.filter { movement ->

                val matchesFilter = when (selectedFilter) {
                    "Ingresos" -> movement.isIncome
                    "Gastos" -> !movement.isIncome
                    else -> true
                }

                val matchesSearch = movement.title.contains(
                    searchText,
                    ignoreCase = true
                ) || movement.category.contains(
                    searchText,
                    ignoreCase = true
                )

                matchesFilter && matchesSearch
            }

            if (filteredMovements.isNotEmpty()) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(Dimens.padding16)
                ) {
                    Text(
                        text = date,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Bold
                    )

                    filteredMovements.forEach { movement ->
                        MovementItem(
                            title = movement.title,
                            description = movement.category,
                            amount = movement.amount,
                            color = if (movement.isIncome) {
                                AppTheme.colors.finTrackIncome
                            } else {
                                AppTheme.colors.finTrackExpense
                            },
                            backgroundColor = if (movement.isIncome) {
                                AppTheme.colors.finTrackIncomeBackground
                            } else {
                                AppTheme.colors.finTrackExpenseBackground
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .size(Dimens.height64)
                    .background(
                        color = AppTheme.colors.backgrounds.actionBlue,
                        shape = RoundedCornerShape(Dimens.corner24)
                    )
                    .clickable {
                        
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar movimiento",
                    tint = AppTheme.colors.text.white
                )
            }
        }
    }
}