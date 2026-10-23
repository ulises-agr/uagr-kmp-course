package com.uagr.kmp.course.presentation.ui.register.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.buton.ButtonCustom
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.field.TextFieldCustom
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.component.text.TextNormal
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.register_button
import course.shared.generated.resources.register_confipass
import course.shared.generated.resources.register_email
import course.shared.generated.resources.register_name
import course.shared.generated.resources.register_pass
import course.shared.generated.resources.register_subtitle
import course.shared.generated.resources.register_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterContainer(
    name: String = "",
    onNameChange: (String) -> Unit = {},
    email: String = "",
    onEmailChange: (String) -> Unit = {},
    password: String = "",
    onPasswordChange: (String) -> Unit = {},
    repeatPassword: String = "",
    onrepeatPasswordChange: (String) -> Unit = {},
    onClickRegister: () -> Unit = {},
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.padding16)
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.spacedBy(Dimens.padding16),
        horizontalAlignment = Alignment.Start,
    ) {
        Spacer(Modifier.height(Dimens.height28))
        TextBigBold(
            text = stringResource(Res.string.register_title),
            modifier = Modifier.align(Alignment.Start),
            color = AppTheme.colors.text.blue,
        )
        TextNormal(
            modifier = Modifier.align(Alignment.Start),
            color = AppTheme.colors.text.grayBlue,
            text = stringResource(Res.string.register_subtitle),
        )
        Spacer(modifier = Modifier.height(Dimens.height16))

        TextFieldCustom(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            placeholderColor = AppTheme.colors.text.grayBlue,
            placeholder = stringResource(Res.string.register_name),
            onValueChange = onNameChange,
        )
        Spacer(modifier = Modifier.height(Dimens.height8))
        TextFieldCustom(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            placeholderColor = AppTheme.colors.text.grayBlue,
            placeholder = stringResource(Res.string.register_email),
            onValueChange = onEmailChange,
        )
        Spacer(modifier = Modifier.height(Dimens.height8))
        TextFieldCustom(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            placeholderColor = AppTheme.colors.text.grayBlue,
            placeholder = stringResource(Res.string.register_pass),
            onValueChange = onPasswordChange,
        )
        Spacer(modifier = Modifier.height(Dimens.height8))
        TextFieldCustom(
            modifier = Modifier.fillMaxWidth(),
            value = repeatPassword,
            placeholderColor = AppTheme.colors.text.grayBlue,
            placeholder = stringResource(Res.string.register_confipass),
            onValueChange = onrepeatPasswordChange,
        )
        Spacer(modifier = Modifier.height(Dimens.height28))
        ButtonCustom(
            onClick = onClickRegister,
            modifier = Modifier.fillMaxWidth()
                .height(Dimens.height52),
            backgroundButton = AppTheme.colors.primary,
            textColor = AppTheme.colors.backgrounds.white,
            text = stringResource(Res.string.register_button),
            shape = RoundedCornerShape(size = Dimens.corner14)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginContainerPreview() {
    SafeScreenContainerTest {
        RegisterContainer()
    }
}
