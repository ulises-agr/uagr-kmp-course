/*
 * Dialog.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.component.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.buton.ButtonCustom
import com.uagr.kmp.course.presentation.component.text.TextNormalBold
import com.uagr.kmp.course.presentation.component.text.TextSmall
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.example
import org.jetbrains.compose.resources.stringResource

@Composable
fun DialogCustom(
    titleTextColor: Color,
    titleText: String,
    messageTextColor: Color,
    messageText: String,
    primaryButtonBackgroundColor: Color,
    primaryButtonTextColor: Color,
    primaryButtonText: String,
    secondaryButtonBackgroundColor: Color = Color.Gray,
    secondaryButtonTextColor: Color = Color.White,
    secondaryButtonText: String = "",
    onPrimaryButtonClick: () -> Unit = {},
    onSecondaryButtonClick: () -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            TextNormalBold(
                color = titleTextColor,
                text = titleText,
            )
        },
        text = {
            TextSmall(
                color = messageTextColor,
                text = messageText,
            )
        },
        confirmButton = {
            if (primaryButtonText.isNotEmpty()) {
                ButtonCustom(
                    backgroundButton = primaryButtonBackgroundColor,
                    textColor = primaryButtonTextColor,
                    text = stringResource(Res.string.example),
                    onClick = onPrimaryButtonClick,
                )
            }
        },
        dismissButton = {
            if (secondaryButtonText.isNotEmpty()) {
                ButtonCustom(
                    backgroundButton = secondaryButtonBackgroundColor,
                    textColor = secondaryButtonTextColor,
                    text = stringResource(Res.string.example),
                    onClick = onSecondaryButtonClick,
                )
            }
        },
    )
}

@Preview(
    showBackground = true,
)
@Composable
private fun ErrorDialogPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Dimens.padding16),
        verticalArrangement = Arrangement.spacedBy(Dimens.padding16),
    ) {
        DialogCustom(
            titleTextColor = Color.Black,
            titleText = stringResource(Res.string.example),
            messageTextColor = Color.Black,
            messageText = stringResource(Res.string.example),
            primaryButtonBackgroundColor = Color.Black,
            primaryButtonTextColor = Color.White,
            primaryButtonText = stringResource(Res.string.example),
            secondaryButtonBackgroundColor = Color.Black,
            secondaryButtonTextColor = Color.White,
            secondaryButtonText = stringResource(Res.string.example),
        )
    }
}
