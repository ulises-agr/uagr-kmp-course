/*
 * CardContainer.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.card.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.component.card.Card
import com.uagr.kmp.course.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.theme.AppTheme
import com.uagr.kmp.course.theme.Dimens

@Composable
fun CardContainer(
    onClickButton: () -> Unit = { },
    onClickIcon: () -> Unit = { },
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.padding16)
            .verticalScroll(state = scrollState)
            .background(color = AppTheme.colors.backgrounds.backgroundColor),
        verticalArrangement = Arrangement.spacedBy(
            space = Dimens.padding16,
            alignment = Alignment.CenterVertically,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        
        Card(
            modifier = Modifier,
            colors = CardDefaults.cardColors(
                containerColor = AppTheme.colors.backgrounds.backgroundColor,
                contentColor = AppTheme.colors.text.black,
            ),
            onClickButton = onClickButton,
            onClickIcon = onClickIcon
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardContainerPreview() {
    SafeScreenContainerTest {
        CardContainer()
    }
}
