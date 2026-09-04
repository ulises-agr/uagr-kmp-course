package com.uagr.kmp.course.feature.welcome.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.uagr.kmp.course.core.ui.theme.colors.Black00
import com.uagr.kmp.course.core.ui.theme.Dimens
import com.uagr.kmp.course.core.ui.theme.robotoNormal
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
            .safeContentPadding()
            .fillMaxSize(),
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = Dimens.dp16),
            text = "Welcome to the KMP",
            style = robotoNormal(32.sp,Black00),
        )
        /*
        Button(
            onClick = {
                onEvent(WelcomeUiEvent.OnPaymentClick)
            }
        ) {
            Text("Payment")
        }
        */
        Button(
            onClick = {
                onEvent(WelcomeUiEvent.OnNavigateToHome)
            }
        ) {
            Text("Consumptions")
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