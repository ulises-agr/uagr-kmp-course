package com.uagr.kmp.course.presentation.component.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.uagr.kmp.course.domain.model.summary.SummaryModel
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun SummaryView(
    textTitle: String = "",
    textAmount: String = "",
    incomeEntity: SummaryModel = SummaryModel(),
    expensesEntity: SummaryModel = SummaryModel(),
    paddingBottomColumn: Dp = Dimens.padding20,
) {
    val roundCorner = RoundedCornerShape(size = Dimens.height16)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = Dimens.height4,
                shape = roundCorner,
                clip = true
            )
            .background(
                color = AppTheme.colors.backgrounds.blue,
                shape = roundCorner
            )
            .padding(bottom = paddingBottomColumn)
    ) {
        SummaryHeadView(
            textTitle = textTitle,
            textAmount = textAmount,
        )
        Spacer(modifier = Modifier.height(height = Dimens.height8))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            SummaryHeadView(
                modifier = Modifier.weight(1f),
                textTitle = incomeEntity.title,
                paddingTopTitle = Dimens.paddingNome,
                fontSizeTitle = Dimens.textSizeExtraSmall,
                space = Dimens.padding4,
                textAmount = incomeEntity.amount,
                fontSizeAmount = Dimens.textSizeSmall,
            )
            SummaryHeadView(
                modifier = Modifier.weight(1f),
                textTitle = expensesEntity.title,
                paddingTopTitle = Dimens.paddingNome,
                fontSizeTitle = Dimens.textSizeExtraSmall,
                space = Dimens.padding4,
                textAmount = expensesEntity.amount,
                fontSizeAmount = Dimens.textSizeSmall,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SummaryViewPreview() {
    SafeScreenContainerTest {
        SummaryView(
            textTitle = "Balance total",
            textAmount = "$24,860.00",
            incomeEntity = SummaryModel(title = "Ingresos",
                amount = "+$18,500"),
            expensesEntity = SummaryModel(title = "Gastos",
                amount = "-$8,460"),
        )
    }
}