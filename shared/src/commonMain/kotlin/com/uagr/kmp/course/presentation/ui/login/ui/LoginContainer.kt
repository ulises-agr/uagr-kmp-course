package com.uagr.kmp.course.presentation.ui.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uagr.kmp.course.presentation.theme.Dimens
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.uagr.kmp.course.presentation.component.buton.ButtonCustom
import com.uagr.kmp.course.presentation.theme.AppTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.field.TextFieldCustom
import com.uagr.kmp.course.presentation.component.field.TextFieldPassword
import course.shared.generated.resources.Res
import course.shared.generated.resources.app_name
import course.shared.generated.resources.email
import course.shared.generated.resources.login_balance_available
import course.shared.generated.resources.login_button
import course.shared.generated.resources.login_create_account
import course.shared.generated.resources.login_description
import course.shared.generated.resources.login_loading
import course.shared.generated.resources.password
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginContainer(
    email: String,
    emailError: String?,
    password: String,
    passwordError: String?,
    loginError: String?,
    isLoading: Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onCreateAccountClicked: () -> Unit
) {

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.backgrounds.lightGray)
            .padding(
                horizontal = Dimens.padding32
            )
    ) {
        Spacer(
            modifier = Modifier.height(Dimens.height72)
        )

        Text(
            text = stringResource(Res.string.app_name),
            color = AppTheme.colors.backgrounds.darkBlue,
            fontSize = Dimens.textSizeLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(Dimens.height4)
        )

        Text(
            text = stringResource(Res.string.login_description),
            color = AppTheme.colors.text.secondary,
            fontSize = Dimens.textSizeNormal
        )

        Spacer(
            modifier = Modifier.height(Dimens.height32)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.height230)
                .background(
                    color = AppTheme.colors.backgrounds.darkBlue,
                    shape = RoundedCornerShape(Dimens.corner28)
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.padding24)
            ) {

                Text(
                    text = "\$--",
                    color = AppTheme.colors.text.white,
                    fontSize = Dimens.textSizeLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = stringResource(Res.string.login_balance_available),
                    color = AppTheme.colors.text.white,
                    fontSize = Dimens.textSizeNormal
                )

                Spacer(
                    modifier = Modifier.height(Dimens.height20)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.height78)
                        .background(
                            color = AppTheme.colors.backgrounds.mediumBlue,
                            shape = RoundedCornerShape(Dimens.corner16)
                        )
                ) {

                    val barHeights = listOf(22, 26, 32, 40, 46, 54, 62)

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = Dimens.padding16),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.Bottom
                    ) {

                        barHeights.forEach { height ->
                            BalanceBar(height = height)
                        }
                    }
                }
            }
        }

        Spacer(
            modifier = Modifier.height(Dimens.height40)
        )

        TextFieldCustom(
            value = email,
            onValueChange = onEmailChanged,
            labelColor = AppTheme.colors.text.secondary,
            label = stringResource(Res.string.email),
            placeholderColor = AppTheme.colors.text.secondary,
            placeholder = "",
            error = emailError
        )

        Spacer(
            modifier = Modifier.height(Dimens.height16)
        )

        TextFieldPassword(
            value = password,
            onValueChange = onPasswordChanged,
            passwordVisible = passwordVisible,
            onPasswordVisibleChange = { passwordVisible = it },
            labelColor = AppTheme.colors.text.secondary,
            label = stringResource(Res.string.password),
            placeholderColor = AppTheme.colors.text.secondary,
            placeholder = "",
            keyboardType = KeyboardType.Password,
            capitalization = KeyboardCapitalization.None,
            error = passwordError
        )

        if (loginError != null) {

            Spacer(
                modifier = Modifier.height(Dimens.height8)
            )

            Text(
                text = loginError,
                fontSize = Dimens.textSizeNormal,
                color = AppTheme.colors.status.error
            )
        }

        Spacer(
            modifier = Modifier.height(Dimens.height24)
        )

        ButtonCustom(
            onClick = {
                onLoginClicked()
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundButton = AppTheme.colors.backgrounds.actionBlue,
            height = Dimens.height56,
            shape = RoundedCornerShape(Dimens.corner16),
            textColor = AppTheme.colors.text.white,
            text = if (isLoading) {
                stringResource(Res.string.login_loading)
            } else {
                stringResource(Res.string.login_button)
            },
            textAlign = TextAlign.Start,
            enabled = !isLoading
        )

        Spacer(
            modifier = Modifier.height(Dimens.height28)
        )

        Text(
            text = stringResource(Res.string.login_create_account),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable {
                    onCreateAccountClicked()
                },
            color = AppTheme.colors.backgrounds.actionBlue,
            fontSize = Dimens.textSizeSmall,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun BalanceBar(height: Int) {
    Box(
        modifier = Modifier
            .width(Dimens.width16)
            .height(height.dp)
            .background(
                color = AppTheme.colors.backgrounds.lightBlue,
                shape = RoundedCornerShape(Dimens.corner4)
            )
    )
}

@Preview(showBackground = true)
@Composable
private fun LoginContainerPreview() {
    SafeScreenContainerTest {
        LoginContainer(
            email = "",
            emailError = null,
            password = "",
            passwordError = null,
            loginError = null,
            isLoading = false,
            onEmailChanged = {},
            onPasswordChanged = {},
            onLoginClicked = {},
            onCreateAccountClicked = {}
        )
    }
}
