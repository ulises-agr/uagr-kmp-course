package com.uagr.kmp.course.presentation.component.Header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.AppLinkButton
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.component.text.TextNormal
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.create_account
import org.jetbrains.compose.resources.stringResource

@Composable
fun BigHeader(
    modifier: Modifier = Modifier,
    title: String = "",
    subtitle: String = "",
    colorTitle: Color = AppTheme.colors.text.blue,
    colorSubtitle: Color = AppTheme.colors.text.gray,
    fontSizeTitle: TextUnit = Dimens.textSizeBig,
    fontSizeSubtitle: TextUnit = Dimens.textSizeNormal,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        TextBigBold(
            modifier = Modifier.fillMaxWidth()
                .padding(top = Dimens.padding16),
            fontSize = fontSizeTitle,
            color = colorTitle,
            text = title,
            textAlign = TextAlign.Left,
        )
        TextNormal(
            modifier = Modifier.fillMaxWidth()
                .padding(top = Dimens.padding8),
            fontSize = fontSizeSubtitle,
            color = colorSubtitle,
            text = subtitle,
            textAlign = TextAlign.Left,
        )
    }

}

@Composable
fun BigHeaderInverted(
    title: String = "",
    subtitle: String = "",
    colorTitle: Color = AppTheme.colors.text.blue,
    colorSubtitle: Color = AppTheme.colors.text.gray,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        TextNormal(
            modifier = Modifier.fillMaxWidth()
                .padding(top = Dimens.padding16),
            fontSize = Dimens.textSizeNormal,
            color = colorTitle,
            text = title,
            textAlign = TextAlign.Left,
        )
        TextBigBold(
            modifier = Modifier.fillMaxWidth()
                .padding(top = Dimens.padding8),
            color = colorSubtitle,
            text = subtitle,
            textAlign = TextAlign.Left,
        )
    }
}

@Composable
fun HeaderSections(
    modifier: Modifier = Modifier,
    title: String = "",
    subtitle: String = "",
    colorTitle: Color = AppTheme.colors.text.black,
    colorSubtitle: Color = AppTheme.colors.text.blue,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextBigBold(
            modifier = Modifier.fillMaxWidth()
                .weight(2f),
            fontSize = Dimens.textSizeNormal,
            color = colorTitle,
            text = title,
            textAlign = TextAlign.Left,
        )
        AppLinkButton(
            normalText = "",
            clickableText = subtitle,
            fontSize = Dimens.textSizeExtraSmall,
            textAlign = TextAlign.Right,
            tag = "Header",
            modifier = Modifier.fillMaxWidth()
                .weight(1f),
            linkColor = colorSubtitle,
            onNavigate = onClick,
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun BigHeaderPreview() {
    SafeScreenContainerTest {
        BigHeader(
            title = "Title",
            subtitle = "subtitle"
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun BigHeaderInvertedPreview() {
    SafeScreenContainerTest {
        BigHeaderInverted(
            title = "Title",
            subtitle = "subtitle"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HeaderSectionsPreview() {
    SafeScreenContainerTest {
        HeaderSections(
            title = "Title",
            subtitle = "subtitle"
        )
    }
}