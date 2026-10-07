package com.uagr.kmp.course.presentation.component.Header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.component.text.TextNormal
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun BigHeader(
    title: String = "",
    subtitle: String = "",
    colorTitle: Color = AppTheme.colors.text.blue,
) {
    TextBigBold(
        modifier = Modifier.fillMaxWidth()
            .padding(top = Dimens.padding16),
        color = colorTitle,
        text = title,
        textAlign = TextAlign.Left,
    )
    TextNormal(
        modifier = Modifier.fillMaxWidth()
            .padding(top = Dimens.padding8),
        fontSize = Dimens.textSizeNormal,
        color = AppTheme.colors.text.gray,
        text = subtitle,
        textAlign = TextAlign.Left,
    )
}

@Composable
fun BigHeaderInverted(
    title: String = "",
    subtitle: String = "",
) {
    TextNormal(
        modifier = Modifier.fillMaxWidth()
            .padding(top = Dimens.padding8),
        fontSize = Dimens.textSizeNormal,
        color = AppTheme.colors.subtitleGray,
        text = title,
        textAlign = TextAlign.Left,
    )
    TextBigBold(
        modifier = Modifier.fillMaxWidth()
            .padding(top = Dimens.padding16),
        color = AppTheme.colors.backgroundButton,
        text = subtitle,
        textAlign = TextAlign.Left,
    )
}

@Preview(showBackground = true)
@Composable
private fun BigHeaderPreview() {
    SafeScreenContainerTest {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = Dimens.padding16),
        ) {
            BigHeader(
                title = "Title",
                subtitle = "subtitle"
            )

            BigHeaderInverted(
                title = "Title",
                subtitle = "subtitle"
            )
        }
    }
}
