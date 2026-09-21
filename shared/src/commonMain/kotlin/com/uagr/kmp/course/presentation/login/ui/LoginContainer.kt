/*
 * LoginContainer.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.login.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.button.Button
import com.uagr.kmp.course.presentation.component.card.SimpleCard
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.field.PasswordField
import com.uagr.kmp.course.presentation.component.field.TextField
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.email
import course.shared.generated.resources.email_example
import course.shared.generated.resources.ic_email
import course.shared.generated.resources.ic_password
import course.shared.generated.resources.ic_visibility_off
import course.shared.generated.resources.ic_visibility_on
import course.shared.generated.resources.login
import course.shared.generated.resources.password
import course.shared.generated.resources.password_example
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginContainer(
    email : String = "",
    emailError : String = "",
    onEmailChanged : (String) -> Unit = {},
    password : String = "",
    passwordError : String = "",
    passwordVisible : Boolean = false,
    onPasswordVisibilityChanged : (Boolean) -> Unit = {},
    onPasswordChanged : (String) -> Unit = {},
    onClickLogin : () -> Unit = {}
) {

    val scrollState = rememberScrollState()
    
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Dimens.padding16)
            .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxWidth().weight(1f))
        TextBigBold(
            modifier = Modifier
                .fillMaxWidth(),
            color = AppTheme.colors.backgrounds.black,
            text = stringResource(Res.string.login)
        )
        Spacer(modifier = Modifier.fillMaxWidth().weight(1f))
        SimpleCard(modifierCard =
            Modifier.fillMaxWidth(),
            content = {
                TextField(
                    value = email,
                    onValueChange = onEmailChanged,
                    placeHolder = stringResource(Res.string.email_example),
                    placeHolderColor = AppTheme.colors.cards.cardsSubtitle,
                    leadingIcon = Res.drawable.ic_email,
                    label = stringResource(Res.string.email),
                    labelColor = AppTheme.colors.backgrounds.black,
                    errorText = emailError
                )
                
                Spacer(modifier = Modifier.height(height = Dimens.height16))
                
                PasswordField(
                    value = password,
                    onValueChange = onPasswordChanged,
                    placeHolder = stringResource(Res.string.password_example),
                    placeHolderColor = AppTheme.colors.cards.cardsSubtitle,
                    leadingIcon = Res.drawable.ic_password,
                    trailingIconActive = Res.drawable.ic_visibility_on,
                    trailingIconInActive = Res.drawable.ic_visibility_off,
                    passwordVisible = passwordVisible,
                    onPasswordVisibilityChanged = onPasswordVisibilityChanged,
                    label = stringResource(Res.string.password),
                    labelColor = AppTheme.colors.backgrounds.black,
                    errorText = passwordError
                )
                
                Spacer(modifier = Modifier.height(height = Dimens.height16))
                
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = Dimens.textSizeNormal,
                    color = ButtonDefaults.buttonColors(
                        containerColor = AppTheme.colors.button.enable,
                        contentColor = AppTheme.colors.button.contentEnabled,
                        disabledContainerColor = AppTheme.colors.button.disable,
                        disabledContentColor = AppTheme.colors.button.contentDisabled
                    ),
                    text = "Iniciar Sesión",
                    textAlign = TextAlign.Center,
                    onClickButton = onClickLogin
                )
                
            }
        )
        Spacer(modifier = Modifier.fillMaxWidth().weight(2f))
    }
    
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    SafeScreenContainerTest {
        LoginContainer()
    }
}