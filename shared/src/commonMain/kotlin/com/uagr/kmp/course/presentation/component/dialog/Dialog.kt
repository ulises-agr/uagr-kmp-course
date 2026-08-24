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
import com.uagr.kmp.course.presentation.component.button.ButtonPrimary
import com.uagr.kmp.course.presentation.component.button.ButtonSecondary
import com.uagr.kmp.course.presentation.component.text.TextNormalBold
import com.uagr.kmp.course.presentation.component.text.TextSmall
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.example
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun InfoDialog(
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
                ButtonPrimary(
                    backgroundColor = primaryButtonBackgroundColor,
                    textColors = primaryButtonTextColor,
                    text = primaryButtonText,
                    onClick = onPrimaryButtonClick,
                )
            }
        },
        dismissButton = {
            if (secondaryButtonText.isNotEmpty()) {
                ButtonSecondary(
                    backgroundColor = secondaryButtonBackgroundColor,
                    textColors = secondaryButtonTextColor,
                    text = secondaryButtonText,
                    onClick = onSecondaryButtonClick,
                )
            }
        },
    )
}

@Composable
fun InfoDialog(
    titleTextColor: Color,
    titleText: StringResource,
    messageTextColor: Color,
    messageText: StringResource,
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
                text = stringResource(titleText),
            )
        },
        text = {
            TextSmall(
                color = messageTextColor,
                text = stringResource(messageText),
            )
        },
        confirmButton = {
            if (primaryButtonText.isNotEmpty()) {
                ButtonPrimary(
                    backgroundColor = primaryButtonBackgroundColor,
                    textColors = primaryButtonTextColor,
                    text = primaryButtonText,
                    onClick = onPrimaryButtonClick,
                )
            }
        },
        dismissButton = {
            if (secondaryButtonText.isNotEmpty()) {
                ButtonSecondary(
                    backgroundColor = secondaryButtonBackgroundColor,
                    textColors = secondaryButtonTextColor,
                    text = secondaryButtonText,
                    onClick = onSecondaryButtonClick,
                )
            }
        },
    )
}

@Composable
fun ErrorDialog(
    titleTextColor: Color,
    titleText: String,
    descriptionTextColor: Color,
    descriptionText: String,
    primaryButtonBackgroundColor: Color,
    primaryButtonTextColor: Color,
    primaryButtonText: String,
    onPrimaryButtonClick: () -> Unit = {},
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
                color = descriptionTextColor,
                text = descriptionText,
            )
        },
        confirmButton = {
            ButtonPrimary(
                backgroundColor = primaryButtonBackgroundColor,
                textColors = primaryButtonTextColor,
                text = primaryButtonText,
                onClick = onPrimaryButtonClick,
            )
        },
    )
}

@Composable
fun ErrorDialog(
    titleTextColor: Color,
    titleText: StringResource,
    descriptionTextColor: Color,
    descriptionText: StringResource,
    primaryButtonBackgroundColor: Color,
    primaryButtonTextColor: Color,
    primaryButtonText: StringResource,
    onPrimaryButtonClick: () -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            TextNormalBold(
                color = titleTextColor,
                text = stringResource(titleText),
            )
        },
        text = {
            TextSmall(
                color = descriptionTextColor,
                text = stringResource(descriptionText),
            )
        },
        confirmButton = {
            ButtonPrimary(
                backgroundColor = primaryButtonBackgroundColor,
                textColors = primaryButtonTextColor,
                text = primaryButtonText,
                onClick = onPrimaryButtonClick,
            )
        },
    )
}

@Preview(
    showBackground = true,
)
@Composable
private fun DialogPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Dimens.padding16),
        verticalArrangement = Arrangement.spacedBy(Dimens.padding16),
    ) {
        InfoDialog(
            titleTextColor = Color.Black,
            titleText = stringResource(Res.string.example),
            messageTextColor = Color.Black,
            messageText = stringResource(Res.string.example),
            primaryButtonBackgroundColor = Color.Black,
            primaryButtonTextColor = Color.White,
            primaryButtonText = stringResource(Res.string.example),
            secondaryButtonTextColor = Color.White,
            secondaryButtonBackgroundColor = Color.Gray,
            secondaryButtonText = stringResource(Res.string.example),
        )
    }
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
        ErrorDialog(
            titleTextColor = Color.Black,
            titleText = stringResource(Res.string.example),
            descriptionTextColor = Color.Black,
            descriptionText = stringResource(Res.string.example),
            primaryButtonBackgroundColor = Color.Black,
            primaryButtonTextColor = Color.White,
            primaryButtonText = stringResource(Res.string.example),
        )
    }
}
