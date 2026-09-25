package com.uagr.kmp.course.presentation.component.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.presentation.component.text.TextNormal
import com.uagr.kmp.course.presentation.component.text.TextNormalBold
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.accept
import course.shared.generated.resources.error
import course.shared.generated.resources.please_try_again_later
import org.jetbrains.compose.resources.stringResource

@Composable
fun CustomDialog(
    modifier: Modifier = Modifier,
    errorDialogModel : ErrorDialogModel?,
    titleColor : Color,
    descriptionColor : Color,
    buttonColor : Color,
    onClickButton: () -> Unit = {}
) {

    errorDialogModel?.let { errorDialog ->
        AlertDialog(
            modifier = modifier,
            title = {
                TextNormalBold(
                    text = errorDialog.title,
                    color = titleColor,
                )
            },
            text = {
                TextNormal(
                    text = errorDialog.message,
                    color = descriptionColor,
                )
            },
            confirmButton = {
                if(errorDialog.primaryButtonText.isNotEmpty()){
                    TextNormalBold(
                        modifier = Modifier
                            .clickable{
                                onClickButton()
                            },
                        text = errorDialog.primaryButtonText,
                        color = buttonColor,
                    )
                }
            },
            dismissButton = {
            
            },
            onDismissRequest = {
            
            }
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
        CustomDialog(
            errorDialogModel = ErrorDialogModel(
                title = stringResource(Res.string.error),
                message = stringResource(Res.string.please_try_again_later),
                primaryButtonText = stringResource(Res.string.accept),
            ),
            titleColor = AppTheme.colors.backgrounds.black,
            descriptionColor = AppTheme.colors.backgrounds.black,
            buttonColor = AppTheme.colors.text.link
        )
    }
}