/*
// MenuContainer.kt
// Created by Karla Judith Cervantes Higareda on 10/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.menu.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.card.HomeMenuOption
import com.uagr.kmp.course.presentation.component.card.MenuCard
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.mock.homeDataMock
import com.uagr.kmp.course.presentation.theme.Dimens
import com.uagr.kmp.course.presentation.ui.home.ui.HomeContainer
import com.uagr.kmp.course.presentation.ui.menu.viewmodel.MenuUiState

@Composable
fun MenuContainer(
    uiState: MenuUiState,
    onMenuSelected: (HomeMenuOption) -> Unit,
    content: @Composable () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            content()
        }
        MenuCard(
            modifier = Modifier.padding(
                horizontal = Dimens.padding8,
                vertical = Dimens.padding16,
            ),
            selectedOption = uiState.selectedOption,
            onOptionSelected = onMenuSelected,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuContainerPreview() {
    SafeScreenContainerTest {
        MenuContainer(uiState = MenuUiState(), onMenuSelected = {}) {
        }
    }
}
