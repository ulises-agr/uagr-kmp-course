/*
 * PackagesContainer.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.packages.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.card.PackagesCard
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun PackagesContainer(
    activePackagesClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Dimens.padding16),
        verticalArrangement = Arrangement.spacedBy(
            space = Dimens.padding16,
            alignment = Alignment.CenterVertically,
        ),
    ) {
        PackagesCard(
            onClick = activePackagesClick,
            nameText = "Internet Full",
            typeText = "Paquete Ilimitado",
            quantityText = "580",
            usedText = "MB Usados",
            daysText = "Renueva en 27 Dias / 5 Abril - 4 Mayo",
            buttonText = "Activa Paquete",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PackagesContainerPreview() {
    SafeScreenContainerTest {
        PackagesContainer()
    }
}
