/*
 * PackagesContainer.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.uagr.packages.sdk.domain.model.PackagesDataModel
import com.uagr.packages.sdk.ui.component.PackagesSdkCard
import com.uagr.packages.sdk.ui.theme.PackagesSdkTheme

@Composable
internal fun PackagesContainer(
    packages: List<PackagesDataModel> = emptyList(),
    onPackageClick: () -> Unit = {},
) {
    val colors = PackagesSdkTheme.colors
    val dimens = PackagesSdkTheme.dimens

    if (packages.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = dimens.padding16),
            verticalArrangement = Arrangement.spacedBy(
                space = dimens.padding16,
                alignment = Alignment.CenterVertically,
            ),
        ) {
            items(count = packages.size) { index ->
                val packageItem = packages[index]
                PackagesSdkCard(
                    onClick = onPackageClick,
                    nameText = packageItem.name,
                    typeText = packageItem.description,
                    quantityText = packageItem.price,
                    unit = packageItem.currency,
                    renewalText = packageItem.created_by,
                    buttonText = packageItem.created_at,
                )
            }
        }
    } else {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Empty packages",
            color = colors.onSurface,
            fontSize = dimens.textBigExtra,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}
