package com.uagr.kmp.course.feature.welcome.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.feature.welcome.presentation.WelcomeUiEvent
import com.uagr.kmp.course.feature.welcome.presentation.WelcomeUiState

@Composable
fun WelcomeScreen(
    state: WelcomeUiState,
    onEvent: (WelcomeUiEvent) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .safeContentPadding()
            .fillMaxSize(),
    ) {
        Text(
            text = "Welcome to the KMP Course!",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )

        Button(
            onClick = {
                onEvent(WelcomeUiEvent.OnPaymentClick)
            }
        ) {
            Text("Payment")
        }

        Button(
            onClick = {
                onEvent(WelcomeUiEvent.OnNavigateToHome)
            }
        ) {
            Text("Click me!")
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen(
        state = WelcomeUiState(

        ),
        onEvent = {}
    )
}