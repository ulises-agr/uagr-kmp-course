package com.uagr.kmp.course.presentation.ui.register.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.buton.ButtonCustom
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.uagr.kmp.course.presentation.component.field.TextFieldCustom
import com.uagr.kmp.course.presentation.component.field.TextFieldPassword
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
    registerError: String?,
    isLoading: Boolean,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onRegisterClicked: () -> Unit,
    onBackClicked: () -> Unit
) {

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    var confirmPasswordVisible by remember {
        mutableStateOf(false)
    }

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

        TextFieldCustom(
            value = name,
            onValueChange = onNameChanged,
            labelColor = AppTheme.colors.text.secondary,
            label = "Nombre",
            placeholderColor = AppTheme.colors.text.secondary,
            placeholder = "",
            error = nameError
        )

        Spacer(
            modifier = Modifier.height(Dimens.height16)
        )

        TextFieldCustom(
            value = email,
            onValueChange = onEmailChanged,
            labelColor = AppTheme.colors.text.secondary,
            label = "Correo",
            placeholderColor = AppTheme.colors.text.secondary,
            placeholder = "",
            keyboardType = KeyboardType.Email,
            capitalization = KeyboardCapitalization.None,
            error = emailError
        )

        Spacer(
            modifier = Modifier.height(Dimens.height16)
        )

        TextFieldPassword(
            value = password,
            onValueChange = onPasswordChanged,
            passwordVisible = passwordVisible,
            onPasswordVisibleChange = {
                passwordVisible = it
            },
            labelColor = AppTheme.colors.text.secondary,
            label = "Contraseña",
            placeholderColor = AppTheme.colors.text.secondary,
            placeholder = "",
            keyboardType = KeyboardType.Password,
            capitalization = KeyboardCapitalization.None,
            error = passwordError
        )

        Spacer(
            modifier = Modifier.height(Dimens.height16)
        )

        TextFieldPassword(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChanged,
            passwordVisible = confirmPasswordVisible,
            onPasswordVisibleChange = {
                confirmPasswordVisible = it
            },
            labelColor = AppTheme.colors.text.secondary,
            label = "Confirmar contraseña",
            placeholderColor = AppTheme.colors.text.secondary,
            placeholder = "",
            keyboardType = KeyboardType.Password,
            capitalization = KeyboardCapitalization.None,
            error = confirmPasswordError
        )

        Spacer(
            modifier = Modifier.height(Dimens.height128)
        )

        if (registerError != null) {

            Text(
                text = registerError,
                fontSize = Dimens.textSizeNormal,
                color = AppTheme.colors.status.error
            )

            Spacer(
                modifier = Modifier.height(Dimens.height16)
            )
        }

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
            registerError = null,
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