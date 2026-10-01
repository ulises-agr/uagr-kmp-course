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
    onLoginClicked: () -> Unit
) {

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
            text = "FinTrack",
            color = AppTheme.colors.backgrounds.darkBlue,
            fontSize = Dimens.textSizeLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(Dimens.height4)
        )

        Text(
            text = "Tus finanzas, claras incluso sin conexión.",
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
                    text = "Balance disponible",
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

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChanged,
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.height64),
            placeholder = {
                Text(
                    text = "Correo",
                    fontSize = Dimens.textSizeNormal,
                    color = AppTheme.colors.text.secondary
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(Dimens.corner16),
            textStyle = TextStyle(
                fontSize = Dimens.textSizeNormal,
                fontWeight = FontWeight.Normal
            ),
            isError = emailError != null
        )

        Spacer(
            modifier = Modifier.height(Dimens.height16)
        )

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChanged,
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.height64),
            placeholder = {
                Text(
                    text = "Contraseña",
                    fontSize = Dimens.textSizeNormal,
                    color = AppTheme.colors.text.secondary
                )
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(Dimens.corner16),
            textStyle = TextStyle(
                fontSize = Dimens.textSizeNormal,
                fontWeight = FontWeight.Normal
            ),
            isError = passwordError != null
        )

        if (loginError != null) {

            Spacer(
                modifier = Modifier.height(Dimens.height8)
            )

            Text(
                text = loginError,
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
            text = if (isLoading) "Cargando..." else "Iniciar sesión",
            textAlign = TextAlign.Start,
            enabled = !isLoading
        )

        Spacer(
            modifier = Modifier.height(Dimens.height28)
        )

        Text(
            text = "Crear una cuenta",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable {
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
    LoginContainer(
        email = "",
        emailError = null,
        password = "",
        passwordError = null,
        loginError = null,
        isLoading = false,
        onEmailChanged = {},
        onPasswordChanged = {},
        onLoginClicked = {}
    )
}