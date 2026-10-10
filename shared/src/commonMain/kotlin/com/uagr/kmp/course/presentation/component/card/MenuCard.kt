/*
// MenuCard.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.buton.MenuButton
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.ic_Inicio
import course.shared.generated.resources.ic_Presupuesto
import course.shared.generated.resources.ic_example
import course.shared.generated.resources.menu_home
import course.shared.generated.resources.menu_transactions
import course.shared.generated.resources.menu_budget
import course.shared.generated.resources.menu_goals
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.stringResource

enum class HomeMenuOption {
    HOME,
    TRANSACTIONS,
    BUDGET,
    GOALS,
}

@Composable
fun MenuCard(
    modifier: Modifier = Modifier,
    selectedOption: HomeMenuOption = HomeMenuOption.HOME,
    onOptionSelected: (HomeMenuOption) -> Unit = {},
    homeIcon: DrawableResource = Res.drawable.ic_Inicio,
    transactionsIcon: DrawableResource = Res.drawable.ic_example,
    budgetIcon: DrawableResource = Res.drawable.ic_Presupuesto,
    goalsIcon: DrawableResource = Res.drawable.ic_example,
    backgroundButtonSelected: Color = AppTheme.colors.backgrounds.blueMenuSelected,
    colorSelected: Color = AppTheme.colors.backgrounds.blue,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.height64),
        shape = RoundedCornerShape(Dimens.corner24),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.backgrounds.white
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HomeMenuOption.entries.forEach { option ->
                val label = when (option) {
                    HomeMenuOption.HOME -> Res.string.menu_home
                    HomeMenuOption.TRANSACTIONS -> Res.string.menu_transactions
                    HomeMenuOption.BUDGET -> Res.string.menu_budget
                    HomeMenuOption.GOALS -> Res.string.menu_goals
                }
                val icon = when (option) {
                    HomeMenuOption.HOME -> homeIcon
                    HomeMenuOption.TRANSACTIONS -> transactionsIcon
                    HomeMenuOption.BUDGET -> budgetIcon
                    HomeMenuOption.GOALS -> goalsIcon
                }
                MenuButton(
                    modifier = Modifier.weight(1f),
                    onClick = { onOptionSelected(option) },
                    isSelected = selectedOption == option,
                    backgroundButton = backgroundButtonSelected,
                    textColor = colorSelected,
                    iconColor = colorSelected,
                    text = stringResource(label),
                    icon = icon,
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun MenuCardPreview() {
    SafeScreenContainerTest {
        var selectedOption by remember { mutableStateOf(HomeMenuOption.HOME) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = Dimens.padding16),
            verticalArrangement = Arrangement.spacedBy(Dimens.padding16),
        ) {
            MenuCard(
                selectedOption = selectedOption,
                onOptionSelected = { selectedOption = it },
            )
        }
    }
}
