package com.uagr.kmp.course.presentation.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.uagr.kmp.course.presentation.component.buton.ButtonCustom
import com.uagr.kmp.course.presentation.component.card.SimpleCard
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.field.TextFieldCustom
import com.uagr.kmp.course.presentation.component.field.TextFieldPassword
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.component.text.TextNormal
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.email
import course.shared.generated.resources.email_example
import course.shared.generated.resources.ic_email
import course.shared.generated.resources.ic_finance_illustration
import course.shared.generated.resources.ic_password
import course.shared.generated.resources.ic_visibility_off
import course.shared.generated.resources.ic_visibility_on
import course.shared.generated.resources.login
import course.shared.generated.resources.password
import course.shared.generated.resources.password_example
import course.shared.generated.resources.subtitle_login
import course.shared.generated.resources.title_login
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginContainer(
    email: String = "",
    onEmailChange: (String) -> Unit = {},
    password: String = "",
    onPasswordChange: (String) -> Unit = {},
    passwordVisible: Boolean = false,
    onPasswordVisibleChange: (Boolean) -> Unit = {},
    onLoginClick: () -> Unit = {},
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = Dimens.padding28,
                end = Dimens.padding28)
            .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextBigBold(
            modifier = Modifier.fillMaxWidth()
                .padding(top = Dimens.padding16),
            color = AppTheme.colors.backgroundButton,
            text = stringResource(Res.string.title_login),
            textAlign = TextAlign.Left,
        )
        TextNormal(
            modifier = Modifier.fillMaxWidth()
                .padding(top = Dimens.padding16),
            fontSize = Dimens.textSizeNormal,
            color = AppTheme.colors.subtitleGray,
            text = stringResource(Res.string.subtitle_login),
            textAlign = TextAlign.Left,
        )
        Spacer(modifier = Modifier.height(height = Dimens.height32))
        Image(
            painter = painterResource(resource = Res.drawable.ic_finance_illustration),
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight()
                .weight(3f),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(height = Dimens.height16))

        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(top = Dimens.padding16),
            verticalArrangement = Arrangement.spacedBy(
                space = Dimens.padding16,
                alignment = Alignment.CenterVertically,
            ),
        ) {
            TextFieldCustom(
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
                email = password,
                onEmailChange = onPasswordChange,
                passwordVisible = passwordVisible,
                onPasswordVisibleChange = onPasswordVisibleChange,
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
                    },
                ),
            )
            Spacer(modifier = Modifier.height(height = Dimens.height16))
            ButtonCustom(
                onClick = onLoginClick,
                backgroundButton = AppTheme.colors.backgroundButton,
                textColor = AppTheme.colors.backgrounds.white,
                text = stringResource(Res.string.login),
            )
            Spacer(modifier = Modifier.height(height = Dimens.height8))
        }

        Spacer(modifier = Modifier.fillMaxWidth().weight(2f))
    }
}

@Preview(showBackground = true)
@Composable
fun LoginContainerPreview() {
    SafeScreenContainerTest {
        LoginContainer()
    }
}
