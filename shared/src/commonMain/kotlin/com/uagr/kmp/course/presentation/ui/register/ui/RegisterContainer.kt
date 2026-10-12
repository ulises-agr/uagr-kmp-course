/*
 * RegisterContainer.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.register.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.field.CustomOutlinedTextField
import com.uagr.kmp.course.presentation.component.icon.IconCustom
import com.uagr.kmp.course.presentation.theme.AppTheme
import course.shared.generated.resources.Res
import course.shared.generated.resources.backText
import course.shared.generated.resources.confirmPassword_PlaceHolder
import course.shared.generated.resources.emailRegister
import course.shared.generated.resources.hidePassword
import course.shared.generated.resources.login_description
import course.shared.generated.resources.login_title
import course.shared.generated.resources.nameRegister
import course.shared.generated.resources.passwordRegister
import course.shared.generated.resources.register_description
import course.shared.generated.resources.register_title
import course.shared.generated.resources.showPassword
import course.shared.generated.resources.userRegister
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterContainer(
    name: String = "",
    nameError: String? = null,
    onNameChange: (String) -> Unit = {},
    onNameFocusLost: () -> Unit = {},
    email: String = "",
    emailError: String? = null,
    onEmailChange: (String) -> Unit = {},
    onEmailFocusLost: () -> Unit = {},
    password: String = "",
    passwordError: String? = null,
    onPasswordChange: (String) -> Unit = {},
    onPasswordFocusLost: () -> Unit = {},
    confirmPassword: String = "",
    confirmPasswordError: String? = null,
    onConfirmPasswordChange: (String) -> Unit = {},
    onConfirmPasswordFocusLost: () -> Unit = {},
    isPasswordVisible: Boolean = false,
    onPasswordVisibleChange: () -> Unit = {},
    isConfirmPasswordVisible: Boolean = false,
    onConfirmPasswordVisibleChange: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            IconCustom(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(Res.string.backText),
                tint = AppTheme.colors.text.black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(Res.string.register_title),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = AppTheme.colors.primary
        )

        Text(
            text = stringResource(Res.string.register_description),
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
        )

        CustomOutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            errorMessage = nameError,
            onFocusLost = onNameFocusLost,
            label = stringResource(Res.string.nameRegister),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            singleLine = true
        )

        CustomOutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            errorMessage = emailError,
            onFocusLost = onEmailFocusLost,
            label = stringResource(Res.string.emailRegister),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            singleLine = true
        )

        CustomOutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            errorMessage = passwordError,
            onFocusLost = onPasswordFocusLost,
            label = stringResource(Res.string.passwordRegister),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                Text(
                    text = if (isPasswordVisible) stringResource(Res.string.hidePassword) else stringResource(Res.string.showPassword),
                    color = AppTheme.colors.text.link,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .clickable { onPasswordVisibleChange() }
                        .padding(end = 12.dp)
                )
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            singleLine = true
        )

        CustomOutlinedTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            errorMessage = confirmPasswordError,
            onFocusLost = onConfirmPasswordFocusLost,
            label = stringResource(Res.string.confirmPassword_PlaceHolder),
            visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                Text(
                    text = if (isConfirmPasswordVisible) stringResource(Res.string.hidePassword) else stringResource(Res.string.showPassword),
                    color = AppTheme.colors.text.link,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .clickable { onConfirmPasswordVisibleChange() }
                        .padding(end = 12.dp)
                )
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
            singleLine = true
        )

        Button(
            onClick = onRegisterClick,
            colors = ButtonDefaults.buttonColors(containerColor = AppTheme.colors.primary),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text =stringResource(Res.string.userRegister), fontSize = 16.sp, color = AppTheme.colors.text.white)
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
