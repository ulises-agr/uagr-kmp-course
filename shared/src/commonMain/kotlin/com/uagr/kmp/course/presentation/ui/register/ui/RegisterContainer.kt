package com.uagr.kmp.course.presentation.ui.register.ui

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.presentation.component.Header.BigHeader
import com.uagr.kmp.course.presentation.component.buton.ButtonCustom
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.field.TextFieldCustom
import com.uagr.kmp.course.presentation.component.field.TextFieldPassword
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.component.text.TextNormal
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import com.uagr.kmp.course.utils.operators.StatusLoading
import course.shared.generated.resources.Res
import course.shared.generated.resources.create_account
import course.shared.generated.resources.email
import course.shared.generated.resources.email_example
import course.shared.generated.resources.ic_visibility_off
import course.shared.generated.resources.ic_visibility_on
import course.shared.generated.resources.password
import course.shared.generated.resources.password_example
import course.shared.generated.resources.register_button_title
import course.shared.generated.resources.register_confirm_password_placeholder
import course.shared.generated.resources.register_email_placeholder
import course.shared.generated.resources.register_name_placeholder
import course.shared.generated.resources.register_password_placeholder
import course.shared.generated.resources.register_subtitle
import course.shared.generated.resources.register_title
import course.shared.generated.resources.subtitle_login
import course.shared.generated.resources.title_login
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterContainer(
    name: String = "",
    onNameChange: (String) -> Unit = {},
    email: String = "",
    onEmailChange: (String) -> Unit = {},
    password: String = "",
    onPasswordChange: (String) -> Unit = {},
    passwordVisible: Boolean = false,
    onPasswordVisibleChange: (Boolean) -> Unit = {},
    confirmPassword: String = "",
    onPasswordConfirmChange: (String) -> Unit = {},
    passwordConfirmVisible: Boolean = false,
    onPasswordConfirmVisibleChange: (Boolean) -> Unit = {},
    onRegisterClick: () -> Unit = {},
) {

    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Dimens.padding16)
            .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxWidth())
        BigHeader(title = stringResource(Res.string.register_title),
            subtitle = stringResource(Res.string.register_subtitle))
        Spacer(modifier = Modifier.height(height = Dimens.height32))


        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                space = Dimens.padding16,
                alignment = Alignment.CenterVertically,
            ),
        ) {
            TextFieldCustom(
                value = name,
                onValueChange = onNameChange,
                labelColor = AppTheme.colors.text.black,
                label = stringResource(Res.string.register_name_placeholder),
                placeholderColor = AppTheme.colors.placeHolderButton,
                placeholder = stringResource(Res.string.register_name_placeholder),
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
            )
            TextFieldCustom(
                value = email,
                onValueChange = onEmailChange,
                labelColor = AppTheme.colors.text.black,
                label = stringResource(Res.string.register_email_placeholder),
                placeholderColor = AppTheme.colors.placeHolderButton,
                placeholder = stringResource(Res.string.register_email_placeholder),
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
            )
            TextFieldPassword(
                email = password,
                onEmailChange = onPasswordChange,
                passwordVisible = passwordVisible,
                onPasswordVisibleChange = onPasswordVisibleChange,
                labelColor = AppTheme.colors.placeHolderButton,
                label = stringResource(Res.string.register_password_placeholder),
                placeholderColor = AppTheme.colors.text.black,
                placeholder = stringResource(Res.string.register_password_placeholder),
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
            TextFieldPassword(
                email = confirmPassword,
                onEmailChange = onPasswordConfirmChange,
                passwordVisible = passwordConfirmVisible,
                onPasswordVisibleChange = onPasswordConfirmVisibleChange,
                labelColor = AppTheme.colors.placeHolderButton,
                label = stringResource(Res.string.register_confirm_password_placeholder),
                placeholderColor = AppTheme.colors.text.black,
                placeholder = stringResource(Res.string.register_confirm_password_placeholder),
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
            Spacer(modifier = Modifier.height(height = Dimens.height128))
            ButtonCustom(
                onClick = onRegisterClick,
                backgroundButton = AppTheme.colors.backgroundButton,
                textColor = AppTheme.colors.backgrounds.white,
                text = stringResource(Res.string.register_button_title),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterContainerPreview() {
    SafeScreenContainerTest {
        RegisterContainer()
    }
}
