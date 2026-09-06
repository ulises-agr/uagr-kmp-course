/*
 * LoginContainerMobile.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.login.ui

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.button.ButtonPrimary
import com.uagr.kmp.course.presentation.component.button.ButtonSecondary
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.field.TextFieldPassword
import com.uagr.kmp.course.presentation.component.field.TextFieldSimple
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.component.text.TextMedium
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.email
import course.shared.generated.resources.email_example
import course.shared.generated.resources.go_back
import course.shared.generated.resources.ic_email
import course.shared.generated.resources.ic_password
import course.shared.generated.resources.ic_visibility_off
import course.shared.generated.resources.ic_visibility_on
import course.shared.generated.resources.login
import course.shared.generated.resources.password
import course.shared.generated.resources.password_example
import course.shared.generated.resources.welcome_to_login_enter_your_credentials
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginContainerMobile(
    email: String = "",
    password: String = "",
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onNavigateToDashboard: () -> Unit = {},
    onNavigateToWelcome: () -> Unit = {},
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.padding16)
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.spacedBy(
            space = Dimens.padding16,
            alignment = Alignment.CenterVertically,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextBigBold(
            modifier = Modifier.fillMaxWidth(),
            color = AppTheme.colors.text.black,
            text = stringResource(Res.string.login),
        )
        TextMedium(
            modifier = Modifier.fillMaxWidth(),
            color = AppTheme.colors.text.black,
            text = stringResource(Res.string.welcome_to_login_enter_your_credentials),
        )
        TextFieldSimple(
            value = email,
            onValueChange = onEmailChange,
            labelColor = AppTheme.colors.text.black,
            label = stringResource(Res.string.email),
            placeholderColor = AppTheme.colors.text.black,
            placeholder = stringResource(Res.string.email_example),
            leadingIcon = Res.drawable.ic_email,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
        )
        TextFieldPassword(
            value = password,
            onValueChange = onPasswordChange,
            labelColor = AppTheme.colors.text.black,
            label = stringResource(Res.string.password),
            placeholderColor = AppTheme.colors.text.black,
            placeholder = stringResource(Res.string.password_example),
            leadingIcon = Res.drawable.ic_password,
            trailingIconActive = Res.drawable.ic_visibility_on,
            trailingIconInActive = Res.drawable.ic_visibility_off,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions(
                onAny = {
                    focusManager.clearFocus()
                    onNavigateToDashboard()
                },
            ),
        )
        Spacer(modifier = Modifier.height(Dimens.height16))
        ButtonPrimary(
            modifier = Modifier.padding(start = Dimens.padding16, end = Dimens.padding16),
            backgroundColor = AppTheme.colors.primary,
            textColors = AppTheme.colors.text.white,
            text = Res.string.login,
            onClick = onNavigateToDashboard,
        )
        ButtonSecondary(
            modifier = Modifier.padding(start = Dimens.padding16, end = Dimens.padding16),
            backgroundColor = AppTheme.colors.secondary,
            textColors = AppTheme.colors.text.white,
            text = Res.string.go_back,
            onClick = onNavigateToWelcome,
        )
        Spacer(modifier = Modifier.height(Dimens.height16))
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun LoginContainerMobilePreview() {
    SafeScreenContainerTest {
        LoginContainerMobile()
    }
}
