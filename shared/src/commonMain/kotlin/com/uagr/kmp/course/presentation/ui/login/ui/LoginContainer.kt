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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uagr.kmp.course.presentation.theme.Dimens
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.uagr.kmp.course.presentation.component.buton.ButtonCustom

@Composable
fun LoginContainer(
    email: String,
    emailError: String?,
    password: String,
    passwordError: String?,
    isLoading: Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = Dimens.padding24,
                vertical = Dimens.padding24
            )
    ) {
        Text(
            text = "FinTrack"
        )

        Spacer(
            modifier = Modifier.height(Dimens.height4)
        )

        Text(
            text = "Tus finanzas, claras incluso sin conexión."
        )

        Spacer(
            modifier = Modifier.height(Dimens.height32)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.height196)
                .background(
                    color = Color(0xFF174EA6),
                    shape = RoundedCornerShape(Dimens.border24)
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.padding24)
            ) {

                Text(
                    text = "\$--",
                    color = Color.White
                )

                Text(
                    text = "Balance disponible",
                    color = Color.White
                )

                Spacer(
                    modifier = Modifier.height(Dimens.height16)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.height64)
                        .background(
                            color = Color(0xFF2F6CC5),
                            shape = RoundedCornerShape(Dimens.border16)
                        )
                ) {

                    val barHeights = listOf(16, 22, 28, 34, 40, 46, 52)

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
            modifier = Modifier.height(Dimens.height32)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { newValue ->
                onEmailChanged(newValue)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Correo")
            },
            singleLine = true,
            isError = emailError != null,
            supportingText = {
                if (emailError != null) {
                    Text(text = emailError)
                }
            }
        )

        Spacer(
            modifier = Modifier.height(Dimens.height16)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { newValue ->
                onPasswordChanged(newValue)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Contraseña")
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError != null,
            supportingText = {
                if (passwordError != null) {
                    Text(text = passwordError)
                }
            }
        )

        Spacer(
            modifier = Modifier.height(Dimens.height24)
        )

        ButtonCustom(
            onClick = {
                onLoginClicked()
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundButton = Color(0xFF2374EA),
            textColor = Color.White,
            text = if (isLoading) "Cargando..." else "Iniciar sesión",
            enabled = !isLoading
        )

        Spacer(
            modifier = Modifier.height(Dimens.height16)
        )

        Text(
            text = "Crear una cuenta",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable {

                },
            color = Color(0xFF2374EA)
        )
    }
}

@Composable
private fun BalanceBar(height: Int) {
    Box(
        modifier = Modifier
            .width(16.dp)
            .height(height.dp)
            .background(
                color = Color(0xFF75AEF8),
                shape = RoundedCornerShape(Dimens.height4)
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
        isLoading = false,
        onEmailChanged = {},
        onPasswordChanged = {},
        onLoginClicked = {}
    )
}