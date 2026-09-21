/*
 * PackageContainer.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.packages.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.card.PackageCard
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.domain.model.packages.PackageModel
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun CardContainer(
    packages: PackageModel? = null,
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
        
        packages?.let {
            PackageCard(
                modifier = Modifier,
                colors = CardDefaults.cardColors(
                    containerColor = AppTheme.colors.backgrounds.backgroundColor,
                    contentColor = AppTheme.colors.text.black,
                ),
                onClickButton = onClickButton,
                onClickIcon = onClickIcon,
                title = packages.title,
                packageDescription = packages.packageDescription,
                usedAmount = packages.usedAmount,
                unit = packages.unit,
                renewalText = packages.renewalText,
                buttonText = packages.buttonText,
                hasInfoIcon = packages.hasInfoIcon
            )
        } ?: run {
            TextBigBold(
                modifier = Modifier.fillMaxWidth(),
                color = AppTheme.colors.backgrounds.black,
                text = "Paquete Vacio",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardContainerPreview() {
    SafeScreenContainerTest {
        CardContainer()
    }
}
