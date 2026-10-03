/*
// LoginContainer.kt
// Created by Karla Judith Cervantes Higareda on 29/09/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.login.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.buton.ButtonCustom
import com.uagr.kmp.course.presentation.component.card.LoginCard
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.field.TextFieldCustom
import com.uagr.kmp.course.presentation.component.field.TextFieldPassword
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.component.text.TextMedium
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.ic_visibility_off
import course.shared.generated.resources.ic_visibility_on
import course.shared.generated.resources.login
import course.shared.generated.resources.name
import course.shared.generated.resources.new_user
import course.shared.generated.resources.password
import course.shared.generated.resources.subtitle
import course.shared.generated.resources.title
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginContainer(
    email: String = "",
    password: String = "",
    passwordVisible: Boolean = false,
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onPasswordVisibleChange: (Boolean) -> Unit = {},
    navigateToHome: () -> Unit = {},
    navigateToRegister: () -> Unit = {},
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus(force = true)
                }
            }
            .padding(horizontal = Dimens.padding28)
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        Spacer(modifier = Modifier.height(Dimens.height80))
        TextBigBold(
            modifier = Modifier.fillMaxWidth(),
            fontSize = Dimens.textSizeBigExtra,
            color = AppTheme.colors.text.blue,
            text = stringResource(Res.string.title),
            textAlign = TextAlign.Start,
        )
        Spacer(modifier = Modifier.height(Dimens.height8))
        TextMedium(
            modifier = Modifier.fillMaxWidth(),
            fontSize = Dimens.textSizeNormal,
            color = AppTheme.colors.text.gray,
            text = stringResource(Res.string.subtitle),
            textAlign = TextAlign.Start,
        )
        Spacer(modifier = Modifier.height(Dimens.height40))
        LoginCard()
        Spacer(modifier = Modifier.height(Dimens.height48))
        TextFieldCustom(
            value = email,
            onValueChange = onEmailChange,
            labelColor = AppTheme.colors.text.gray,
            label = stringResource(Res.string.name),
            placeholderColor = AppTheme.colors.text.gray,
            placeholder = stringResource(Res.string.name),
            imeAction = ImeAction.Next,
        )
        Spacer(modifier = Modifier.height(Dimens.height12))
        TextFieldPassword(
            value = password,
            onEmailChange = onPasswordChange,
            passwordVisible = passwordVisible,
            onPasswordVisibleChange = {
                onPasswordVisibleChange(!passwordVisible)
            },
            labelColor = AppTheme.colors.text.gray,
            label = stringResource(Res.string.password),
            placeholderColor = AppTheme.colors.text.gray,
            placeholder = stringResource(Res.string.password),
            trailingIconActive = Res.drawable.ic_visibility_off,
            trailingIconInActive = Res.drawable.ic_visibility_on,
            keyboardType = KeyboardType.Password,
            capitalization = KeyboardCapitalization.None,
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions(
                onAny = {
                    focusManager.clearFocus()
                },
            ),
        )
        Spacer(modifier = Modifier.height(Dimens.height40))
        ButtonCustom(
            onClick = navigateToHome,
            modifier = Modifier.fillMaxWidth(),
            backgroundButton = AppTheme.colors.backgrounds.blue,
            textColor = AppTheme.colors.text.white,
            text = stringResource(Res.string.login),
        )
        Spacer(modifier = Modifier.height(Dimens.height12))
        ButtonCustom(
            onClick = navigateToRegister,
            modifier = Modifier.fillMaxWidth(),
            backgroundButton = Color.Transparent,
            textColor = AppTheme.colors.text.blue,
            text = stringResource(Res.string.new_user),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginContainerPreview() {
    SafeScreenContainerTest {
        LoginContainer()
    }
}