/*
 * RegisterContainer.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.register.ui

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uagr.kmp.course.presentation.component.icon.IconCustom
import com.uagr.kmp.course.presentation.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterContainer(
    name: String = "",
    onNameChange: (String) -> Unit = {},
    email: String = "",
    onEmailChange: (String) -> Unit = {},
    password: String = "",
    onPasswordChange: (String) -> Unit = {},
    confirmPassword: String = "",
    onConfirmPasswordChange: (String) -> Unit = {},
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
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                IconCustom(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Regresar",
                    tint = AppTheme.colors.text.black
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "FinTrack",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = AppTheme.colors.primary
        )

        Text(
            text = "Regístrate de forma gratuita y segura.",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Correo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = { Text("Confirmar contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
            Text(text = "Registrar usuario", fontSize = 16.sp, color = AppTheme.colors.text.white)
        }
    }
}