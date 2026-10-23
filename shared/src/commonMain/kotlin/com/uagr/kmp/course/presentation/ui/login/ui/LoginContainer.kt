/*
 * LoginContainer.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.buton.ButtonCustom
import com.uagr.kmp.course.presentation.component.card.BalanceCard
import com.uagr.kmp.course.presentation.component.card.BalanceWithImageCard
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.field.TextFieldCustom
import com.uagr.kmp.course.presentation.component.field.TextFieldPassword
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.component.text.TextNormal
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.login_balance
import course.shared.generated.resources.login_button
import course.shared.generated.resources.login_email
import course.shared.generated.resources.login_newAccount
import course.shared.generated.resources.login_pass
import course.shared.generated.resources.login_subtitle
import course.shared.generated.resources.login_title
import course.shared.generated.resources.mini_chart
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginContainer(
    email: String = "",
    onEmailChange: (String) -> Unit = {},
    password: String = "",
    onPasswordChange: (String) -> Unit = {},
    navigateToRegister: () -> Unit = {},
    onLoginClick: () -> Unit = {},
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.padding16)
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.spacedBy(Dimens.padding16),
        horizontalAlignment = Alignment.Start,
    ) {
        Spacer(Modifier.height(Dimens.height28))
        TextBigBold(
            text = stringResource(Res.string.login_title),
            modifier = Modifier.align(Alignment.Start),
            color = AppTheme.colors.text.blue,
        )
        TextNormal(
            modifier = Modifier.align(Alignment.Start),
            color = AppTheme.colors.text.grayBlue,
            text = stringResource(Res.string.login_subtitle),
        )
        Spacer(modifier = Modifier.height(Dimens.height4))
        BalanceWithImageCard(
            modifierColum = Modifier.background( AppTheme.colors.primary),
            title = "",
            totalBalance = "$--",
            subtitle = stringResource(Res.string.login_balance),
            image = Res.drawable.mini_chart,
        )
        Spacer(modifier = Modifier.height(Dimens.height8))
        TextFieldCustom(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            placeholderColor = AppTheme.colors.text.grayBlue,
            placeholder = stringResource(Res.string.login_email),
            onValueChange = onEmailChange,
        )
        TextFieldPassword(
            value = password,
            onValueChange = onPasswordChange,
            labelColor = AppTheme.colors.text.grayBlue,
            label = stringResource(Res.string.login_pass),
            placeholderColor = AppTheme.colors.text.grayBlue,
            placeholder = stringResource(Res.string.login_pass),
        )
        Spacer(modifier = Modifier.height(Dimens.height20))
        ButtonCustom(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth()
                .height(Dimens.height52),
            backgroundButton = AppTheme.colors.primary,
            textColor = AppTheme.colors.backgrounds.white,
            text = stringResource(Res.string.login_button),
            shape = RoundedCornerShape(size = Dimens.corner14)
        )
        TextButton(
            onClick = navigateToRegister,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.textButtonColors(
                contentColor =  AppTheme.colors.text.lightBlue,
            ),
        ){
            Text(
                text = stringResource(Res.string.login_newAccount),
                fontSize = Dimens.textSizeNormal
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginContainerPreview() {
    SafeScreenContainerTest {
        LoginContainer()
    }
}
