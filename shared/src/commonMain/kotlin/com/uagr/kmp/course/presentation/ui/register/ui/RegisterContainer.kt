package com.uagr.kmp.course.presentation.ui.register.ui

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
import com.uagr.kmp.course.presentation.component.buton.BackButton
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
import course.shared.generated.resources.arrow_back
import course.shared.generated.resources.ic_visibility_off
import course.shared.generated.resources.ic_visibility_on
import course.shared.generated.resources.login
import course.shared.generated.resources.name
import course.shared.generated.resources.password
import course.shared.generated.resources.register_confirm_password
import course.shared.generated.resources.register_email
import course.shared.generated.resources.register_name
import course.shared.generated.resources.register_password
import course.shared.generated.resources.register_subtitle
import course.shared.generated.resources.register_title
import course.shared.generated.resources.register_user
import course.shared.generated.resources.subtitle
import course.shared.generated.resources.title
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.stringResource

/*
// RegisterContainer.kt
// Created by Karla Judith Cervantes Higareda on 29/09/26.
// Copyright (c) 2026. All rights reserved.
*/

@Composable
fun RegisterContainer(
    name: String = "",
    email: String = "",
    password: String = "",
    confirmPassword: String = "",
    passwordVisible: Boolean = false,
    confirmPasswordVisible: Boolean = false,
    onNameChange: (String) -> Unit = {},
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onConfirmPasswordChange: (String) -> Unit = {},
    onPasswordVisibleChange: (Boolean) -> Unit = {},
    onConfirmPasswordVisibleChange: (Boolean) -> Unit = {},
    onCreateRegister: (String, String, String, String) -> Unit = { _, _, _, _ -> },
    navigateToBack: () -> Unit = {},
    isLoading: Boolean = false,
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
        BackButton(
            onClick = navigateToBack,
            icon = Res.drawable.arrow_back,
        )
        Spacer(modifier = Modifier.height(Dimens.height12))
        TextBigBold(
            modifier = Modifier.fillMaxWidth(),
            fontSize = Dimens.textSizeBigExtra,
            color = AppTheme.colors.text.blue,
            text = stringResource(Res.string.register_title),
            textAlign = TextAlign.Start,
        )
        Spacer(modifier = Modifier.height(Dimens.height8))
        TextMedium(
            modifier = Modifier.fillMaxWidth(),
            fontSize = Dimens.textSizeNormal,
            color = AppTheme.colors.text.gray,
            text = stringResource(Res.string.register_subtitle),
            textAlign = TextAlign.Start,
        )
        Spacer(modifier = Modifier.height(Dimens.height32))
        TextFieldCustom(
            value = name,
            onValueChange = onNameChange,
            labelColor = AppTheme.colors.text.black,
            label = stringResource(Res.string.register_name),
            placeholderColor = AppTheme.colors.text.black,
            placeholder = stringResource(Res.string.register_name),
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            )
        )
        Spacer(modifier = Modifier.height(Dimens.height24))
        TextFieldCustom(
            value = email,
            onValueChange = onEmailChange,
            labelColor = AppTheme.colors.text.black,
            label = stringResource(Res.string.register_email),
            placeholderColor = AppTheme.colors.text.black,
            placeholder = stringResource(Res.string.register_email),
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            )
        )
        Spacer(modifier = Modifier.height(Dimens.height24))
        TextFieldPassword(
            value = password,
            onEmailChange = onPasswordChange,
            passwordVisible = passwordVisible,
            onPasswordVisibleChange = onPasswordVisibleChange,
            labelColor = AppTheme.colors.text.gray,
            label = stringResource(Res.string.register_password),
            placeholderColor = AppTheme.colors.text.gray,
            placeholder = stringResource(Res.string.register_password),
            trailingIconActive = Res.drawable.ic_visibility_off,
            trailingIconInActive = Res.drawable.ic_visibility_on,
            keyboardType = KeyboardType.Password,
            capitalization = KeyboardCapitalization.None,
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            )
        )
        Spacer(modifier = Modifier.height(Dimens.height24))
        TextFieldPassword(
            value = confirmPassword,
            onEmailChange = onConfirmPasswordChange,
            passwordVisible = confirmPasswordVisible,
            onPasswordVisibleChange = onConfirmPasswordVisibleChange,
            labelColor = AppTheme.colors.text.gray,
            label = stringResource(Res.string.register_confirm_password),
            placeholderColor = AppTheme.colors.text.gray,
            placeholder = stringResource(Res.string.register_confirm_password),
            trailingIconActive = Res.drawable.ic_visibility_off,
            trailingIconInActive = Res.drawable.ic_visibility_on,
            keyboardType = KeyboardType.Password,
            capitalization = KeyboardCapitalization.None,
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus(force = true)
                }
            )
        )
        Spacer(modifier = Modifier.height(Dimens.height128))
        ButtonCustom(
            onClick = {
                focusManager.clearFocus(force = true)
                onCreateRegister(name, email, password, confirmPassword)
            },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth(),
            backgroundButton = AppTheme.colors.backgrounds.blue,
            textColor = AppTheme.colors.text.white,
            text = stringResource(Res.string.register_user),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterContainerPreview() {
    SafeScreenContainerTest {
        RegisterContainer()
    }
}