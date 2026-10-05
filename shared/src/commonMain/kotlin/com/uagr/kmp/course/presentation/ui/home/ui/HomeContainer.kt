package com.uagr.kmp.course.presentation.ui.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.domain.model.home.HomeDataModel
import com.uagr.kmp.course.presentation.component.Header.BigHeader
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextNormal
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun HomeContainer(
    homeData: HomeDataModel? = null,
) {
    val scrollState = rememberScrollState()
    Scaffold (
        modifier = Modifier.fillMaxWidth()
    ){ paddingValues ->

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Dimens.padding16)
            .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxWidth())
        BigHeader(
            title = "Home",
            subtitle = "Bienvenido a FinTrack",
        )
        Spacer(modifier = Modifier.height(height = Dimens.height32))

        TextNormal(
            modifier = Modifier.fillMaxWidth(),
            color = AppTheme.colors.backgrounds.black,
            text = homeData?.description.takeIf { !it.isNullOrBlank() } ?: "Inicio de la aplicación",
            textAlign = TextAlign.Left,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContainerPreview() {
    SafeScreenContainerTest {
        HomeContainer()
    }
}
