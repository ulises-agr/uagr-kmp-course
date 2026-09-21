package com.uagr.kmp.course.presentation.component.field

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextMedium
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.ic_email
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun TextField(
    value : String,
    onValueChange : (String) -> Unit,
    fontSize: TextUnit = Dimens.textSizeNormal,
    placeHolder : String,
    placeHolderColor : Color,
    placeHolderTextAlign : TextAlign = TextAlign.Start,
    leadingIcon: DrawableResource? = null,
    
    ) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontSize = fontSize,
            fontWeight = FontWeight.Normal
        ),
        placeholder = {
            TextMedium(
                color = placeHolderColor,
                text = placeHolder,
                textAlign = placeHolderTextAlign
            )
        },
        leadingIcon = {
            leadingIcon?.let {
                Icon(
                    painter = painterResource(leadingIcon),
                    contentDescription = null
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun TextFieldPreview() {
    SafeScreenContainerTest {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(all = Dimens.padding16)
        ,) {
            TextField(
                value = "Email",
                onValueChange = {},
                leadingIcon = Res.drawable.ic_email,
                placeHolder = "Correo Electronico",
                placeHolderColor = AppTheme.colors.backgrounds.black,
            )
        }
    }
}