/*
 * PackagesContainer.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.packages.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.domain.model.packages.PackagesDataModel
import com.uagr.kmp.course.presentation.component.card.PackagesCard
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.empty_packages
import course.shared.generated.resources.example
import org.jetbrains.compose.resources.stringResource

@Composable
fun PackagesContainer(
    packages: PackagesDataModel? = null,
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
        packages?.let {
            PackagesCard(
                onClick = activePackagesClick,
                nameText = packages.name,
                typeText = packages.description,
                quantityText = packages.price,
                unit = packages.currency,
                renewalText = packages.created_by,
                buttonText = packages.created_at,
            )
        } ?: run {
            TextBigBold(
                modifier = Modifier.fillMaxWidth(),
                color = AppTheme.colors.backgrounds.black,
                text = stringResource(Res.string.empty_packages),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PackagesContainerPreview() {
    SafeScreenContainerTest {
        PackagesContainer(
            packages = PackagesDataModel(
                id = "1",
                name = stringResource(Res.string.example),
                description = stringResource(Res.string.example),
                price = stringResource(Res.string.example),
                currency = stringResource(Res.string.example),
                stock = stringResource(Res.string.example),
                created_by = stringResource(Res.string.example),
                created_at = stringResource(Res.string.example),
            )
        )
    }
}
