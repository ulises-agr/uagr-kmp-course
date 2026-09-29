/*
 * loginContainer.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uagr.kmp.course.presentation.component.buton.ButtonCustom
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.component.text.TextNormal
import com.uagr.kmp.course.presentation.component.text.TextNormalBold
import com.uagr.kmp.course.presentation.component.text.TextSmall
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.login_title
import course.shared.generated.resources.available_balance
import course.shared.generated.resources.balance_text
import course.shared.generated.resources.login_link_text
import course.shared.generated.resources.email_example
import course.shared.generated.resources.login_description
import course.shared.generated.resources.login
import course.shared.generated.resources.placeholder_email
import org.jetbrains.compose.resources.stringResource

@Composable
fun loginContainer(
    email: String = "",
    onEmailChange: (String) -> Unit = {},
    password: String = "",
    onPasswordChange: (String) -> Unit = {},
    passwordVisible: Boolean = false,
    onPasswordVisibleChange: (Boolean) -> Unit = {},
    onLoginClick: () -> Unit = {},
    onCreateAccountClick: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    val fieldShape = RoundedCornerShape(size = Dimens.corner16)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.padding24),
        horizontalAlignment = Alignment.Start,
    ) {
        Spacer(modifier = Modifier.height(height = Dimens.height24))

        TextBigBold(
            modifier = Modifier.fillMaxWidth(),
            color = AppTheme.colors.text.navy,
            text = stringResource(Res.string.login_title),
            textAlign = TextAlign.Start,
            fontSize = Dimens.textSizeBigExtra,
        )
        Spacer(modifier = Modifier.height(height = Dimens.height8))
        TextNormal(
            modifier = Modifier.fillMaxWidth(),
            color = AppTheme.colors.text.gray,
            text = stringResource(Res.string.login_description),
            textAlign = TextAlign.Start,
        )

        Spacer(modifier = Modifier.height(height = Dimens.height24))

        FinTrackBalanceCard()

        Spacer(modifier = Modifier.height(height = Dimens.height32))

        LoginTextField(
            value = email,
            onValueChange = onEmailChange,
            placeholder = stringResource(Res.string.email_example),
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            shape = fieldShape,
        )

        Spacer(modifier = Modifier.height(height = Dimens.height12))

        LoginTextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = stringResource(Res.string.placeholder_email),
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() },
            ),
            shape = fieldShape,
            trailingContent = {
                Text(
                    modifier = Modifier
                        .clickable { onPasswordVisibleChange(!passwordVisible) }
                        .padding(start = Dimens.padding8),
                    text = if (passwordVisible) "Ocultar" else "Ver",
                    color = AppTheme.colors.text.link,
                    style = TextStyle(
                        fontSize = Dimens.textSizeSmall,
                        fontWeight = FontWeight.Medium,
                    ),
                )
            },
        )

        Spacer(modifier = Modifier.height(height = Dimens.height24))

        ButtonCustom(
            onClick = {
                focusManager.clearFocus()
                onLoginClick()
            },
            backgroundButton = AppTheme.colors.primary,
            textColor = AppTheme.colors.text.white,
            text = stringResource(Res.string.login),
            height = Dimens.height48,
            shape = RoundedCornerShape(size = Dimens.corner16),
        )

        Spacer(modifier = Modifier.weight(1f))

        TextNormalBold(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onCreateAccountClick)
                .padding(vertical = Dimens.padding24),
            color = AppTheme.colors.text.link,
            text = stringResource(Res.string.login_link_text),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun FinTrackBalanceCard() {
    val cardShape = RoundedCornerShape(size = Dimens.corner24)
    val chartHeights = listOf(0.28f, 0.36f, 0.42f, 0.50f, 0.58f, 0.68f, 0.78f, 0.92f)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = cardShape)
            .background(color = AppTheme.colors.backgrounds.blue)
            .padding(all = Dimens.padding24),
    ) {
        Text(
            text = stringResource(Res.string.balance_text),
            color = AppTheme.colors.text.white,
            style = TextStyle(
                fontSize = Dimens.textSizeBigExtra,
                fontWeight = FontWeight.Bold,
            ),
        )
        Spacer(modifier = Modifier.height(height = Dimens.height4))
        TextSmall(
            color = AppTheme.colors.text.white.copy(alpha = 0.9f),
            text = stringResource(Res.string.available_balance),
            textAlign = TextAlign.Start,
        )

        Spacer(modifier = Modifier.height(height = Dimens.height24))


    }
}

@Composable
private fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    shape: RoundedCornerShape,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    trailingContent: (@Composable () -> Unit)? = null,
) {
    val borderColor = AppTheme.colors.outlineVariant
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = Dimens.height48)
            .clip(shape = shape)
            .background(color = AppTheme.colors.backgrounds.white)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = shape,
            )
            .padding(horizontal = Dimens.padding16),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(modifier = Modifier.weight(1f)) {
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    color = AppTheme.colors.text.gray,
                    style = TextStyle(
                        fontSize = Dimens.textSizeNormal,
                        fontWeight = FontWeight.Normal,
                    ),
                )
            }
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = Dimens.textSizeNormal,
                    fontWeight = FontWeight.Normal,
                    color = AppTheme.colors.text.black,
                ),
                cursorBrush = SolidColor(AppTheme.colors.primary),
                visualTransformation = visualTransformation,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction,
                ),
                keyboardActions = keyboardActions,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        trailingContent?.invoke()
    }
}

@Preview(showBackground = true)
@Composable
fun loginContainerPreview() {
    SafeScreenContainerTest {
        loginContainer()
    }
}
