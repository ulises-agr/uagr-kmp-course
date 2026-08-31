/*
 * WelcomeContainer.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.welcome.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.component.button.Button
import com.uagr.kmp.course.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.component.text.TextBigBold
import com.uagr.kmp.course.component.text.TextMedium
import com.uagr.kmp.course.theme.AppTheme
import com.uagr.kmp.course.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.explore_our_multiplatform_platform
import course.shared.generated.resources.hello
import course.shared.generated.resources.welcomeButton
import org.jetbrains.compose.resources.stringResource

@Composable
fun WelcomeContainer(
    onClickButton: () -> Unit = { }
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.padding16)
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.spacedBy(
            space = Dimens.padding16,
            alignment = Alignment.CenterVertically,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextBigBold(
            modifier = Modifier.fillMaxWidth(),
            color = AppTheme.colors.text.black,
            text = stringResource(Res.string.hello),
        )
        TextMedium(
            modifier = Modifier.fillMaxWidth(),
            color = AppTheme.colors.text.black,
            text = stringResource(Res.string.explore_our_multiplatform_platform),
        )
        Spacer(modifier = Modifier.height(Dimens.height16))
        Button(
            modifier = Modifier,
            fontSize = Dimens.textSizeNormal,
            color = ButtonDefaults.buttonColors(
                containerColor = AppTheme.colors.button.enable,
                contentColor = AppTheme.colors.button.contentEnabled,
                disabledContainerColor = AppTheme.colors.button.disable,
                disabledContentColor = AppTheme.colors.button.contentDisabled
            ),
            text = stringResource(Res.string.welcomeButton),
            textAlign = TextAlign.Center,
            onClickButton = onClickButton
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeContainerPreview() {
    SafeScreenContainerTest {
        WelcomeContainer()
    }
}
