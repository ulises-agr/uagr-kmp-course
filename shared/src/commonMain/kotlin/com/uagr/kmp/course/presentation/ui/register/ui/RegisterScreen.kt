package com.uagr.kmp.course.presentation.ui.register.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.dialog.DialogCustom
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.ui.register.viewmodel.RegisterUiEvent
import com.uagr.kmp.course.presentation.ui.register.viewmodel.RegisterViewModel
import com.uagr.kmp.course.utils.flow.CollectWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit = {},
    viewModel: RegisterViewModel = koinViewModel()
) {
    val registerUiState by viewModel.registerUiState.collectAsStateWithLifecycle()
    viewModel.registerUiEvent.CollectWithLifecycle {event ->
        when(event){
            is RegisterUiEvent.Idle -> {}
            is RegisterUiEvent.RegisterSuccess -> {
                viewModel.resetUiEvent()
                onRegisterSuccess()
            }
            is RegisterUiEvent.ShowVersionInfoDialog -> {}
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
            repeatPassword = registerUiState.repeatpassword,
            onrepeatPasswordChange = { repeatpassword ->
                viewModel.updateRepeatPassword(repeatpassword = repeatpassword)
            },
            onClickRegister = {
                viewModel.validateRegisterForm(
                    name = registerUiState.name,
                    email = registerUiState.email,
                    password = registerUiState.password,
                    repeatpassword = registerUiState.repeatpassword
                )
            }
        )

        DialogCustom(
            errorDialog = registerUiState.errorDialog,
            titleTextColor = AppTheme.colors.text.blue,
            messageTextColor = AppTheme.colors.text.blue,
            primaryButtonTextColor = AppTheme.colors.text.white,
            primaryButtonBackgroundColor = AppTheme.colors.primary,
            onPrimaryButtonClick = {
                viewModel.dismissErrorDialog()
            }
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_9,
)
@Composable
private fun WelcomeScreenPreview() {
    SafeScreenContainerTest {
        RegisterScreen()
    }
}