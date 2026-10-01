package com.uagr.kmp.course.presentation.ui.register.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun RegisterContainer() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = Dimens.padding32
            )
    ) {

        Text(
            text = "Crear cuenta"
        )
    }
}