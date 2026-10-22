/*
 * Dialog.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.component.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.presentation.component.buton.ButtonCustom
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextNormalBold
import com.uagr.kmp.course.presentation.component.text.TextSmall
import com.uagr.kmp.course.presentation.theme.AppTheme

@Composable
fun DialogCustom(
    modifier: Modifier = Modifier,
    errorDialog: ErrorDialogModel?,
    titleTextColor: Color,
    messageTextColor: Color,
    primaryButtonBackgroundColor: Color,
    primaryButtonTextColor: Color,
    secondaryButtonBackgroundColor: Color = Color.Gray,
    secondaryButtonTextColor: Color = Color.White,
    onPrimaryButtonClick: () -> Unit = {},
    onSecondaryButtonClick: () -> Unit = {},
) {
    errorDialog?.let { errorDialog ->
        AlertDialog(
            modifier = modifier,
            onDismissRequest = {},
            title = {
                TextNormalBold(
                    color = titleTextColor,
                    text = errorDialog.title,
                )
            },
            text = {
                TextSmall(
                    color = messageTextColor,
                    text = errorDialog.message,
                )
            },
            confirmButton = {
                if (errorDialog.primaryButtonText.isNotEmpty()) {
                    ButtonCustom(
                        backgroundButton = primaryButtonBackgroundColor,
                        textColor = primaryButtonTextColor,
                        text = errorDialog.primaryButtonText,
                        onClick = onPrimaryButtonClick,
                    )
                }
            },
            dismissButton = {
                if (errorDialog.secondaryButtonText.isNotEmpty()) {
                    ButtonCustom(
                        backgroundButton = secondaryButtonBackgroundColor,
                        textColor = secondaryButtonTextColor,
                        text = errorDialog.secondaryButtonText,
                        onClick = onSecondaryButtonClick,
                    )
                }
            },
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 640
)
@Composable
private fun ErrorDialogPreview() {
    SafeScreenContainerTest {
        Box(modifier = Modifier.fillMaxSize()) {
            DialogCustom(
                errorDialog = ErrorDialogModel(
                    title = "Prueba",
                    message = "Este es un mensaje de error.",
                    primaryButtonText = "Aceptar",
                    secondaryButtonText = "",
                ),
                titleTextColor = AppTheme.colors.text.black,
                messageTextColor = AppTheme.colors.text.black,
                primaryButtonBackgroundColor = AppTheme.colors.primary,
                primaryButtonTextColor = AppTheme.colors.text.white,
                secondaryButtonBackgroundColor = AppTheme.colors.text.gray,
                secondaryButtonTextColor = AppTheme.colors.text.white,
            )
        }
    }
}
