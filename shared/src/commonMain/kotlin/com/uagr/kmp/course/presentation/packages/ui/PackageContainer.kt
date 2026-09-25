/*
 * PackageContainer.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.packages.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.domain.model.packages.PackageDataModel
import com.uagr.kmp.course.presentation.component.card.PackageCard
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextNormalBold
import com.uagr.kmp.course.presentation.mock.packagesDataListMock
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun CardContainer(
    packages: List<PackageDataModel> = emptyList(),
    onClickButton: () -> Unit = { },
    onClickIcon: () -> Unit = { },
) {
    if(packages.isNotEmpty()){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.padding16)
                .background(color = AppTheme.colors.backgrounds.backgroundColor),
            verticalArrangement = Arrangement.spacedBy(
                space = Dimens.padding16,
                alignment = Alignment.CenterVertically,
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(count = packages.size) { index ->
                packages[index].let { packageItem ->
                    PackageCard(
                        modifier = Modifier,
                        colors = CardDefaults.cardColors(
                            containerColor = AppTheme.colors.backgrounds.backgroundColor,
                            contentColor = AppTheme.colors.text.black,
                        ),
                        onClickButton = onClickButton,
                        onClickIcon = onClickIcon,
                        title = packageItem.name,
                        packageDescription = packageItem.description,
                        usedAmount = packageItem.price,
                        unit = packageItem.currency,
                        renewalText = packageItem.created_by,
                        buttonText = "Aceptar",
                        hasInfoIcon = true
                    )
                }
            }
        }
    } else {
        Column( modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.padding16)
            .background(color = AppTheme.colors.backgrounds.backgroundColor),
            verticalArrangement = Arrangement.spacedBy(
                space = Dimens.padding16,
                alignment = Alignment.CenterVertically,
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.fillMaxWidth().weight(1f))
            TextNormalBold(
                modifier = Modifier.fillMaxWidth(),
                color = AppTheme.colors.backgrounds.black,
                text = "Empty Packages"
            )
            Spacer(modifier = Modifier.fillMaxWidth().weight(1f))
        }
        
    }
}



@Preview(showBackground = true)
@Composable
private fun PackagesContainerPreview() {
    SafeScreenContainerTest {
        CardContainer(packages = emptyList())
    }
}