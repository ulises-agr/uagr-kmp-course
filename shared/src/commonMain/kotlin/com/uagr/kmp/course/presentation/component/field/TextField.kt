/*
 * TextField.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.component.field

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
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
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.example
import course.shared.generated.resources.ic_example
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TextFieldCustom(
    modifier: Modifier = Modifier,
    email: String,
    fontSize: TextUnit = Dimens.textSizeSmall,
    onValueChange: (String) -> Unit,
    labelColor: Color,
    label: String,
    labelTextAlign: TextAlign = TextAlign.Start,
    placeholderColor: Color,
    placeholder: String,
    placeholderTextAlign: TextAlign = TextAlign.Start,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    var isFocused by remember { mutableStateOf(false) }


    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged {
                isFocused = it.isFocused
            },
        value = email,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontSize = fontSize,
            fontWeight = FontWeight.Normal,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction,
            capitalization = capitalization
        ),
        keyboardActions = keyboardActions,
        label = { if (isFocused || email.isNotEmpty()) {
            TextSmall(
                color = labelColor,
                text = label,
                textAlign = labelTextAlign,
            )
        } else {
            TextMedium(
                color = labelColor,
                text = label,
                textAlign = labelTextAlign,
            )
        }
        },
        placeholder = {
            TextMedium(
                color = placeholderColor,
                text = placeholder,
                textAlign = placeholderTextAlign,
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(Dimens.corner14),
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedContainerColor = AppTheme.colors.backgrounds.white,
            unfocusedContainerColor = AppTheme.colors.backgrounds.white,
            disabledContainerColor = AppTheme.colors.backgrounds.white,
            focusedIndicatorColor =  AppTheme.colors.backgrounds.grey,
        ),
    )
}

@Composable
fun TextFieldPassword(
    modifier: Modifier = Modifier,
    password: String,
    onEmailChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibleChange: (Boolean) -> Unit,
    fontSize: TextUnit = Dimens.textSizeSmall,
    labelColor: Color,
    label: String,
    labelTextAlign: TextAlign = TextAlign.Start,
    placeholderColor: Color,
    placeholder: String,
    placeholderTextAlign: TextAlign = TextAlign.Start,
    trailingIconActive: DrawableResource,
    trailingIconInActive: DrawableResource,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.Words,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged {
                isFocused = it.isFocused
            },
        value = password,
        onValueChange = onEmailChange,
        textStyle = TextStyle(
            fontSize = fontSize,
            fontWeight = FontWeight.Normal,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction,
            capitalization = capitalization
        ),
        keyboardActions = keyboardActions,
        label = { if (isFocused || password.isNotEmpty()) {
            TextSmall(
                color = labelColor,
                text = label,
                textAlign = labelTextAlign,
            )
        } else {
            TextMedium(
                color = labelColor,
                text = label,
                textAlign = labelTextAlign,
            )
        }
        },
        placeholder = {
            TextMedium(
                color = placeholderColor,
                text = placeholder,
                textAlign = placeholderTextAlign,
            )
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { onPasswordVisibleChange(!passwordVisible) }) {
                Icon(
                    painter = painterResource(if (passwordVisible) trailingIconActive else trailingIconInActive),
                    contentDescription = null,
                )
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(Dimens.corner14),
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedContainerColor = AppTheme.colors.backgrounds.white,
            unfocusedContainerColor = AppTheme.colors.backgrounds.white,
            disabledContainerColor = AppTheme.colors.backgrounds.white,
            focusedIndicatorColor =  AppTheme.colors.backgrounds.grey,
        ),
    )
}

@Preview(
    showBackground = true,
)
@Composable
private fun TextFieldPreview() {
    SafeScreenContainerTest {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = Dimens.padding16),
            verticalArrangement = Arrangement.spacedBy(Dimens.padding16),
        ) {
            Column(
                modifier = Modifier.padding(all = Dimens.padding16),
                verticalArrangement = Arrangement.spacedBy(Dimens.padding16),
            ) {
                TextFieldCustom(
                    email = "",
                    onValueChange = {},
                    labelColor = Color.Black,
                    label = stringResource(Res.string.example),
                    placeholderColor = Color.Black,
                    placeholder = stringResource(Res.string.example),
                )
                TextFieldPassword(
                    password = "",
                    onEmailChange = {},
                    passwordVisible = false,
                    onPasswordVisibleChange = {},
                    labelColor = Color.Black,
                    label = stringResource(Res.string.example),
                    placeholderColor = Color.Black,
                    placeholder = stringResource(Res.string.example),
                    trailingIconActive = Res.drawable.ic_example,
                    trailingIconInActive = Res.drawable.ic_example,
                    keyboardType = KeyboardType.Password,
                    capitalization = KeyboardCapitalization.None,
                )
            }
        }
    }
}
