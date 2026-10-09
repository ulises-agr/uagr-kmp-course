package com.uagr.kmp.course.presentation.component.summary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.component.text.TextMedium
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun SummaryHeadView(
    modifier: Modifier = Modifier,
    textTitle: String = "",
    colorTitle: Color = AppTheme.colors.text.whiteSoft,
    fontSizeTitle: TextUnit = Dimens.textSizeExtraSmall,
    paddingTopTitle: Dp = Dimens.padding22,
    space: Dp = Dimens.height8,
    textAmount: String = "",
    colorAmount: Color = AppTheme.colors.text.white,
    fontSizeAmount: TextUnit = Dimens.textSizeBigExtra,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TextMedium(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = Dimens.padding20,
                    top = paddingTopTitle,
                    end = Dimens.padding20
                ),
            fontSize = fontSizeTitle,
            color = colorTitle,
            text = textTitle,
            textAlign = TextAlign.Left
        )
        Spacer(modifier = Modifier.height(height = space))
        TextBigBold(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = Dimens.padding20,
                    end = Dimens.padding20,
                ),
            fontSize = fontSizeAmount,
            color = colorAmount,
            text = textAmount,
            textAlign = TextAlign.Left
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun SummaryHeadViewPreview() {
    SafeScreenContainerTest {
        SummaryHeadView(
            textTitle = "Balance total",
            textAmount = "$24,860.00"
        )
    }
}