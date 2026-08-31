package com.uagr.kmp.course.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.uagr.kmp.course.theme.AppTheme
import com.uagr.kmp.course.theme.Dimens

@Composable
fun Button(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = Dimens.textSizeNormal,
    color: ButtonColors,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    onClickButton: () -> Unit = { }
) {
    Button(
        modifier = modifier,
        onClick = onClickButton,
        colors = color
    ) {
        Text(
            text = text,
            fontSize    = fontSize,
            textAlign = textAlign
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TextPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Dimens.padding16),
        verticalArrangement = Arrangement.spacedBy(Dimens.padding16),
    ) {
        Button(
            modifier = Modifier,
            fontSize = Dimens.textSizeNormal,
            color = ButtonDefaults.buttonColors(
                containerColor = AppTheme.colors.button.enable,
                contentColor = AppTheme.colors.button.contentEnabled,
                disabledContainerColor = AppTheme.colors.button.disable,
                disabledContentColor = AppTheme.colors.button.contentDisabled
            ),
            text = "Continuar",
            textAlign = TextAlign.Center
        )
    }
}
