package com.uagr.kmp.course.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.core.ui.theme.AppTheme
import com.uagr.kmp.course.core.ui.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.activar_paquete
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun RedButton(
    text: String,
    textColor: Color,
    textStyle: TextStyle = TextStyle(
        color = textColor,
        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        fontStyle = androidx.compose.ui.text.font.FontStyle.Normal,
    ),
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(Dimens.dp16),
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .size(Dimens.dp48)
            .background(color = Color.Red, shape = shape),
        enabled = enabled,
        shape = shape,
    ) {
        Text(
            text,
            style = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.dp4)
        )
    }
}


@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)

private fun Preview() {
    AppTheme {
        RedButton(
            text = stringResource(resource = Res.string.activar_paquete),
            textColor = Color.White,
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.dp16),
        ) {

        }
    }
}