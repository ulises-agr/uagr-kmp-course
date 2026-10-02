package com.uagr.kmp.course.presentation.ui.register.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.dialog.DialogCustom
import com.uagr.kmp.course.presentation.component.loader.Loader
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.ui.register.viewmodel.RegisterUiEvent
import com.uagr.kmp.course.presentation.ui.register.viewmodel.RegisterViewModel
import com.uagr.kmp.course.utils.flow.CollectWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = koinViewModel(),
    onRegisterSuccess: () -> Unit = {},
) {
    val registerUiState by viewModel.registerUiState.collectAsStateWithLifecycle()

    viewModel.registerUiEvent.CollectWithLifecycle { event ->
        when (event) {
            is RegisterUiEvent.Idle -> {}
            is RegisterUiEvent.RegisterSuccess -> {
                viewModel.resetUiEvent()
                onRegisterSuccess()
            }
        }
    }

    SafeScreenContainer {
        RegisterContainer(
            name = registerUiState.name,
            onNameChange = { name ->
                viewModel.updateName(name = name)
            },
            email = registerUiState.email,
            onEmailChange = { email ->
                viewModel.updateEmail(email = email)
            },
            password = registerUiState.password,
            onPasswordChange = { password ->
                viewModel.updatePassword(password = password)
            },
            passwordVisible = registerUiState.passwordVisible,
            onPasswordVisibleChange = { passwordVisible ->
                viewModel.updatePasswordVisible(passwordVisible = passwordVisible)
            },
            confirmPassword = registerUiState.confirmPassword,
            onPasswordConfirmChange = { password ->
                viewModel.updateConfirmPassword(password = password)
            },
            passwordConfirmVisible = registerUiState.confirmPasswordVisible,
            onPasswordConfirmVisibleChange = { passwordVisible ->
                viewModel.updateConfirmPasswordVisible(passwordVisible = passwordVisible)
            },
            onRegisterClick = {
                viewModel.validateRegisterForm(
                    email = registerUiState.email,
                    password = registerUiState.password,
                    name = registerUiState.name,
                    confirmPass = registerUiState.confirmPassword,
                )
            },
        )
        Loader()
        DialogCustom(
            errorDialog = registerUiState.errorDialog,
            titleTextColor = AppTheme.colors.text.black,
            messageTextColor = AppTheme.colors.text.black,
            primaryButtonBackgroundColor = AppTheme.colors.primary,
            primaryButtonTextColor = AppTheme.colors.text.white,
            onPrimaryButtonClick = {
                viewModel.dismissErrorDialog()
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    SafeScreenContainerTest {
        RegisterContainer()
    }
}
