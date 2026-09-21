package com.uagr.kmp.course.presentation.component.field

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextMedium
import com.uagr.kmp.course.presentation.component.text.TextMediumBold
import com.uagr.kmp.course.presentation.component.text.TextSmall
import com.uagr.kmp.course.presentation.component.text.TextSmallExtra
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.ic_email
import course.shared.generated.resources.ic_password
import course.shared.generated.resources.ic_visibility_off
import course.shared.generated.resources.ic_visibility_on
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun TextField(
    value : String,
    onValueChange : (String) -> Unit,
    fontSize: TextUnit = Dimens.textSizeNormal,
    label : String,
    labelColor : Color,
    labelTextAlign : TextAlign = TextAlign.Start,
    placeHolder : String,
    placeHolderColor : Color,
    placeHolderTextAlign : TextAlign = TextAlign.Start,
    errorText : String? = "",
    leadingIcon: DrawableResource? = null,
    imeAction: ImeAction = ImeAction.Done,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.Words, )
{
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontSize = fontSize,
            fontWeight = FontWeight.Normal
        ),
        label = {
            TextMedium(
                color = labelColor,
                text = label,
                textAlign = labelTextAlign,
            )
        },
        placeholder = {
            TextSmall(
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
        },
        supportingText = {
            errorText?.let {
                TextSmallExtra(
                    color = AppTheme.colors.error,
                    text = errorText,
                    textAlign = placeHolderTextAlign
                )
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = imeAction,
            capitalization = capitalization
        
        ),
    )
}

@Composable
fun PasswordField(
    value : String,
    onValueChange : (String) -> Unit,
    fontSize: TextUnit = Dimens.textSizeNormal,
    label : String,
    labelColor : Color,
    labelTextAlign : TextAlign = TextAlign.Start,
    placeHolder : String,
    placeHolderColor : Color,
    placeHolderTextAlign : TextAlign = TextAlign.Start,
    errorText : String? = "",
    leadingIcon: DrawableResource? = null,
    trailingIconActive: DrawableResource,
    trailingIconInActive: DrawableResource,
    passwordVisible : Boolean,
    onPasswordVisibilityChanged : (Boolean) -> Unit,
    imeAction: ImeAction = ImeAction.Done,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.Words,
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontSize = fontSize,
            fontWeight = FontWeight.Normal
        ),
        label = {
            TextMedium(
                color = labelColor,
                text = label,
                textAlign = labelTextAlign,
            )
        },
        placeholder = {
            TextSmall(
                color = placeHolderColor,
                text = placeHolder,
                textAlign = placeHolderTextAlign
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    onPasswordVisibilityChanged(!passwordVisible)
                }
            ){
                Icon(
                    painter = painterResource(if (passwordVisible) trailingIconActive else trailingIconInActive),
                    contentDescription = null)
            }
        },
        leadingIcon = {
            leadingIcon?.let {
                Icon(
                    painter = painterResource(leadingIcon),
                    contentDescription = null
                )
            }
        },
        supportingText = {
            errorText?.let {
                TextSmallExtra(
                    color = AppTheme.colors.error,
                    text = errorText,
                    textAlign = placeHolderTextAlign
                )
            }
        },
        visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction,
            capitalization = capitalization
        
        ),
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
                value = "",
                onValueChange = {},
                leadingIcon = Res.drawable.ic_email,
                label = "Correo Electronico",
                labelColor = AppTheme.colors.backgrounds.black,
                placeHolder = "Correo Electronico",
                placeHolderColor = AppTheme.colors.backgrounds.black,
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(Dimens.height16))
            PasswordField(
                value = "",
                onValueChange = {},
                leadingIcon = Res.drawable.ic_password,
                trailingIconActive = Res.drawable.ic_visibility_off,
                trailingIconInActive = Res.drawable.ic_visibility_on,
                label = "Contraseña",
                labelColor = AppTheme.colors.backgrounds.black,
                placeHolder = "Contraseña",
                placeHolderColor = AppTheme.colors.backgrounds.black,
                passwordVisible = false,
                onPasswordVisibilityChanged = {}
            )
        }
    }
}