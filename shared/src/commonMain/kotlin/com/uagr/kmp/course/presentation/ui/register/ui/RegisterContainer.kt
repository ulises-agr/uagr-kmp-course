package com.uagr.kmp.course.presentation.ui.register.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.buton.ButtonCustom
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun RegisterContainer(
    name: String,
    email: String,
    password: String,
    confirmPassword: String,
    nameError: String?,
    emailError: String?,
    passwordError: String?,
    confirmPasswordError: String?,
    isLoading: Boolean,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onRegisterClicked: () -> Unit,
    onBackClicked: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.padding32)
    ) {

        Spacer(
            modifier = Modifier.height(Dimens.height56)
        )

        Text(
            text = "←",
            fontSize = Dimens.textSizeBig,
            color = AppTheme.colors.text.black,
            modifier = Modifier.clickable {
                onBackClicked()
            }
        )

        Spacer(
            modifier = Modifier.height(Dimens.height16)
        )

        Text(
            text = "FinTrack",
            fontSize = Dimens.textSizeLarge,
            fontWeight = FontWeight.Bold,
            color = AppTheme.colors.backgrounds.darkBlue
        )

        Spacer(
            modifier = Modifier.height(Dimens.height4)
        )

        Text(
            text = "Regístrate de forma gratuita y segura.",
            fontSize = Dimens.textSizeNormal,
            color = AppTheme.colors.outline
        )

        Spacer(
            modifier = Modifier.height(Dimens.height32)
        )

        RegisterTextField(
            value = name,
            placeholder = "Nombre",
            error = nameError,
            onValueChange = onNameChanged
        )

        Spacer(
            modifier = Modifier.height(Dimens.height16)
        )

        RegisterTextField(
            value = email,
            placeholder = "Correo",
            error = emailError,
            onValueChange = onEmailChanged
        )

        Spacer(
            modifier = Modifier.height(Dimens.height16)
        )

        RegisterTextField(
            value = password,
            placeholder = "Contraseña",
            error = passwordError,
            isPassword = true,
            onValueChange = onPasswordChanged
        )

        Spacer(
            modifier = Modifier.height(Dimens.height16)
        )

        RegisterTextField(
            value = confirmPassword,
            placeholder = "Confirmar contraseña",
            error = confirmPasswordError,
            isPassword = true,
            onValueChange = onConfirmPasswordChanged
        )

        Spacer(
            modifier = Modifier.height(Dimens.height128)
        )

        ButtonCustom(
            onClick = onRegisterClicked,
            modifier = Modifier.fillMaxWidth(),
            backgroundButton = AppTheme.colors.backgrounds.actionBlue,
            textColor = AppTheme.colors.text.white,
            height = Dimens.height56,
            shape = RoundedCornerShape(Dimens.corner12),
            text = if (isLoading) "Registrando..." else "Registrar usuario",
            enabled = !isLoading,
            textAlign = TextAlign.Start,
        )
    }
}

@Composable
private fun RegisterTextField(
    value: String,
    placeholder: String,
    error: String?,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit
) {

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.height64),
        placeholder = {
            Text(
                text = placeholder,
                fontSize = Dimens.textSizeMedium
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(Dimens.corner16),
        visualTransformation = if (isPassword && !passwordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },

        trailingIcon = {
            if (isPassword) {
                Text(
                    text = if (passwordVisible) "Ocultar" else "Ver",
                    fontSize = Dimens.textSizeSmall,
                    color = AppTheme.colors.backgrounds.actionBlue,
                    modifier = Modifier
                        .padding(end = Dimens.padding12)
                        .clickable {
                            passwordVisible = !passwordVisible
                        }
                )
            }
        },

        isError = error != null,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AppTheme.colors.backgrounds.actionBlue
        )
    )

    if (error != null) {
        Text(
            text = error,
            color = AppTheme.colors.error,
            fontSize = Dimens.textSizeExtraSmall,
            modifier = Modifier.padding(
                start = Dimens.padding12,
                top = Dimens.padding4
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterContainerPreview() {

    AppTheme(
        isDarkMode = false
    ) {
        RegisterContainer(
            name = "",
            email = "",
            password = "",
            confirmPassword = "",
            nameError = null,
            emailError = null,
            passwordError = null,
            confirmPasswordError = null,
            isLoading = false,
            onNameChanged = {},
            onEmailChanged = {},
            onPasswordChanged = {},
            onConfirmPasswordChanged = {},
            onRegisterClicked = {},
            onBackClicked = {}
        )
    }
}