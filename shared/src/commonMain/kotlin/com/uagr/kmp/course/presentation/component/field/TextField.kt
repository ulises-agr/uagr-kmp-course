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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.uagr.kmp.course.presentation.component.text.TextMedium
import com.uagr.kmp.course.presentation.component.text.TextMediumBold
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.example
import course.shared.generated.resources.ic_example
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TextFieldSimple(
    modifier: Modifier = Modifier,
    value: String,
    fontSize: TextUnit = Dimens.textSizeNormal,
    onValueChange: (String) -> Unit,
    labelColor: Color,
    label: String,
    labelTextAlign: TextAlign = TextAlign.Start,
    placeholderColor: Color,
    placeholder: String,
    placeholderTextAlign: TextAlign = TextAlign.Start,
    leadingIcon: DrawableResource? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
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
        label = {
            TextMediumBold(
                color = labelColor,
                text = label,
                textAlign = labelTextAlign,
            )
        },
        placeholder = {
            TextMedium(
                color = placeholderColor,
                text = placeholder,
                textAlign = placeholderTextAlign,
            )
        },
        leadingIcon = {
            leadingIcon?.let {
                Icon(
                    painter = painterResource(leadingIcon),
                    contentDescription = null,
                )
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
        ),
    )
}

@Composable
fun TextFieldPassword(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    fontSize: TextUnit = Dimens.textSizeNormal,
    labelColor: Color,
    label: String,
    labelTextAlign: TextAlign = TextAlign.Start,
    placeholderColor: Color,
    placeholder: String,
    placeholderTextAlign: TextAlign = TextAlign.Start,
    leadingIcon: DrawableResource? = null,
    trailingIconActive: DrawableResource,
    trailingIconInActive: DrawableResource,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.Words,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    var passwordVisible by rememberSaveable { mutableStateOf(value = false) }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
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
        label = {
            TextMediumBold(
                color = labelColor,
                text = label,
                textAlign = labelTextAlign,
            )
        },
        placeholder = {
            TextMedium(
                color = placeholderColor,
                text = placeholder,
                textAlign = placeholderTextAlign,
            )
        },
        leadingIcon = {
            leadingIcon?.let {
                Icon(
                    painter = painterResource(leadingIcon),
                    contentDescription = null,
                )
            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    painter = painterResource(if (passwordVisible) trailingIconActive else trailingIconInActive),
                    contentDescription = null,
                )
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
        ),
    )
}

@Preview(
    showBackground = true,
)
@Composable
private fun TextFieldPreview() {
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
            TextFieldSimple(
                value = "",
                onValueChange = {},
                labelColor = Color.Black,
                label = stringResource(Res.string.example),
                placeholderColor = Color.Black,
                placeholder = stringResource(Res.string.example),
                leadingIcon = Res.drawable.ic_example,
            )
            TextFieldPassword(
                value = "",
                onValueChange = {},
                labelColor = Color.Black,
                label = stringResource(Res.string.example),
                placeholderColor = Color.Black,
                placeholder = stringResource(Res.string.example),
                leadingIcon = Res.drawable.ic_example,
                trailingIconActive = Res.drawable.ic_example,
                trailingIconInActive = Res.drawable.ic_example,
                keyboardType = KeyboardType.Password,
                capitalization = KeyboardCapitalization.None,
            )
        }
    }
}
