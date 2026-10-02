/*
 * HomeContainer.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.home.ui

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uagr.kmp.course.presentation.theme.AppTheme

@Composable
fun HomeContainer(
    userName: String = "Jorge",
    totalBalance: String = "$24,860.00",
    incomes: String = "+$18,500",
    expenses: String = "-$8,460",
    monthlyExpenses: String = "$8,460",
    monthlySavings: String = "$3,120",
    onLogoutClick: () -> Unit = {}
) {
    var showMenu by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Encabezado y Menú
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Hola, $userName",
                    fontSize = 16.sp,
                    color = AppTheme.colors.text.gray
                )
                Text(
                    text = "Tu panorama financiero",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppTheme.colors.text.navy
                )
            }

            // Menú desplegable
            Box {
                IconButton(onClick = { showMenu = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Opciones",
                        tint = AppTheme.colors.text.black
                    )
                }

                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Cerrar sesión",
                                color = AppTheme.colors.status.info,
                                fontWeight = FontWeight.SemiBold
                            )
                        },
                        onClick = {
                            showMenu = false
                            onLogoutClick()
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Tarjeta Principal (Balance Total)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = AppTheme.colors.backgrounds.blue)
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = "Balance total",
                    fontSize = 14.sp,
                    color = AppTheme.colors.text.white.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = totalBalance,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppTheme.colors.text.white
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Ingresos",
                            fontSize = 12.sp,
                            color = AppTheme.colors.text.white.copy(alpha = 0.8f)
                        )
                        Text(
                            text = incomes,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = AppTheme.colors.text.white
                        )
                    }
                    Column {
                        Text(
                            text = "Gastos",
                            fontSize = 12.sp,
                            color = AppTheme.colors.text.white.copy(alpha = 0.8f)
                        )
                        Text(
                            text = expenses,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = AppTheme.colors.text.white
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sección Este Mes
        Text(
            text = "Este mes",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = AppTheme.colors.text.navy
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Tarjeta Gastos
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = AppTheme.colors.backgrounds.white)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Gastos",
                        fontSize = 12.sp,
                        color = AppTheme.colors.text.gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = monthlyExpenses,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppTheme.colors.text.black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "↓ 12%",
                        fontSize = 12.sp,
                        color = AppTheme.colors.status.success
                    )
                }
            }

            // Tarjeta Ahorro
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = AppTheme.colors.backgrounds.white)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Ahorro",
                        fontSize = 12.sp,
                        color = AppTheme.colors.text.gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = monthlySavings,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppTheme.colors.text.black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "↑ 8%",
                        fontSize = 12.sp,
                        color = AppTheme.colors.status.success
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sección Últimos Movimientos
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Últimos movimientos",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = AppTheme.colors.text.navy
            )
            Text(
                text = "Ver todos",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppTheme.colors.text.link
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Items de ejemplo
        TransactionItem("Supermercado", "Hoy · Alimentación", "-$860", isExpense = true)
        TransactionItem("Nómina", "Ayer · Ingreso", "+$15,500", isExpense = false)
        TransactionItem("Internet", "18 sep · Servicios", "-$599", isExpense = true)

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun TransactionItem(
    title: String,
    subtitle: String,
    amount: String,
    isExpense: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(
                    if (isExpense) AppTheme.colors.status.errorContainer
                    else AppTheme.colors.status.successContainer
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(
                        if (isExpense) AppTheme.colors.status.error
                        else AppTheme.colors.status.success
                    )
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = AppTheme.colors.text.black
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = AppTheme.colors.text.gray
            )
        }

        Text(
            text = amount,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = if (isExpense) AppTheme.colors.status.error else AppTheme.colors.status.success
        )
    }
}
