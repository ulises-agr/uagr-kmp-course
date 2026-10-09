package com.uagr.kmp.course.presentation.component.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.uagr.kmp.course.domain.model.summary.SummaryModel
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun SummaryChipView(
    modifier: Modifier = Modifier,
    incomeEntity: SummaryModel = SummaryModel(),
    percentText: String = "",
    paddingBottomColumn: Dp = Dimens.padding20,
) {
    val roundCorner = RoundedCornerShape(size = Dimens.height16)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = Dimens.height4,
                shape = roundCorner,
                clip = true
            )
            .background(
                color = AppTheme.colors.backgrounds.white,
                shape = roundCorner
            )
            .padding(bottom = paddingBottomColumn)
    ) {

        Spacer(modifier = Modifier.height(height = Dimens.height16))
        SummaryHeadView(
            textTitle = incomeEntity.title,
            paddingTopTitle = Dimens.paddingNome,
            fontSizeTitle = Dimens.textSizeExtraSmall,
            colorTitle = AppTheme.colors.text.gray,
            space = Dimens.padding4,
            textAmount = incomeEntity.amount,
            fontSizeAmount = Dimens.textSizeBig,
            colorAmount = AppTheme.colors.text.black
        )
        TextBigBold(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = Dimens.padding20,
                    end = Dimens.padding20
                ),
            fontSize = Dimens.textSizeExtraSmall,
            color = AppTheme.colors.text.green,
            text = percentText,
            textAlign = TextAlign.Left
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun SummaryChipViewPreview() {
    SafeScreenContainerTest {
        SummaryChipView(
            incomeEntity = SummaryModel(
                title = "Ingresos",
                amount = "+$18,500",
            ),
            percentText = "12%"
        )
    }
}