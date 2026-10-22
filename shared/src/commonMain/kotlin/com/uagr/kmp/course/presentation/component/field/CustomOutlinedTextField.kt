package com.uagr.kmp.course.presentation.component.field

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uagr.kmp.course.presentation.theme.AppTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    errorMessage: String? = null,
    onFocusLost: () -> Unit = {},
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    var hasBeenFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        isError = errorMessage != null,
        supportingText = {
            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        },
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        modifier = modifier.onFocusChanged { focusState ->
            if (focusState.isFocused) {
                hasBeenFocused = true
            } else if (hasBeenFocused) {
                onFocusLost()
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun CustomOutlinedTextFieldPreview() {
    AppTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                var emailText by remember { mutableStateOf("") }
                CustomOutlinedTextField(
                    value = emailText,
                    onValueChange = { email ->
                        emailText = email
                    },
                    label = "Correo electronico",
                    modifier = Modifier.fillMaxWidth()
                )

                var errorText by remember { mutableStateOf("usuario_invalido") }
                CustomOutlinedTextField(
                    value = errorText,
                    onValueChange = { emailError ->
                        errorText = emailError
                    },
                    label = "Correo con error",
                    errorMessage = "Ingresa un correo valido",
                    modifier = Modifier.fillMaxWidth()
                )

                var passwordText by remember { mutableStateOf("Password123") }
                var isPasswordVisible by remember { mutableStateOf(false) }
                CustomOutlinedTextField(
                    value = passwordText,
                    onValueChange = { passwordText = it },
                    label = "Contraseña",
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        Text(
                            text = if (isPasswordVisible) "Ocultar" else "Ver",
                            color = AppTheme.colors.text.link,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .clickable { isPasswordVisible = !isPasswordVisible }
                                .padding(end = 12.dp)
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}