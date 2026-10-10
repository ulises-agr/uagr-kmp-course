package com.uagr.kmp.course.presentation.component.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.SelectableChipColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.uagr.kmp.course.domain.model.summary.SummaryModel
import com.uagr.kmp.course.domain.model.transaction.TransactionModel
import com.uagr.kmp.course.domain.model.transaction.TransactionType
import com.uagr.kmp.course.presentation.component.Header.BigHeader
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.statusTransactionView.StatusTransactionView
import com.uagr.kmp.course.presentation.component.summary.SummaryHeadView
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.movements_subtitle
import course.shared.generated.resources.movements_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun TransactionView(
    transactionModel: TransactionModel = TransactionModel(),
) {
    val roundCorner = RoundedCornerShape(size = Dimens.height16)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.Unspecified,
                shape = roundCorner
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val backgroundColor = when (transactionModel.type) {
            TransactionType.INCOME -> AppTheme.colors.backgrounds.greenSoft
            TransactionType.EXPENSE -> AppTheme.colors.backgrounds.redSoft
            TransactionType.UNKNOWN -> AppTheme.colors.backgrounds.white
        }

        val textColor = when (transactionModel.type) {
            TransactionType.INCOME -> AppTheme.colors.text.green
            TransactionType.EXPENSE -> AppTheme.colors.text.red
            TransactionType.UNKNOWN -> AppTheme.colors.text.black
        }

        val amountPrefix = when (transactionModel.type) {
            TransactionType.INCOME -> "+"
            TransactionType.EXPENSE -> "-"
            TransactionType.UNKNOWN -> ""
        }
        StatusTransactionView(
            modifier = Modifier
                .padding(start = Dimens.padding16, end = Dimens.padding16),
            transactionType = transactionModel.type,
        )
        BigHeader(
            modifier = Modifier.weight(2f),
            title = transactionModel.description,
            subtitle = transactionModel.transactionDate,
            colorTitle = AppTheme.colors.text.black,
            colorSubtitle = AppTheme.colors.text.gray,
            fontSizeTitle = Dimens.textSizeSmall,
            fontSizeSubtitle = Dimens.textSizeExtraSmall
        )
        TextBigBold(
            modifier = Modifier.fillMaxWidth()
                .weight(1f),
            fontSize = Dimens.textSizeNormal,
            color = textColor,
            text = "$amountPrefix$${transactionModel.amount}",
            textAlign = TextAlign.Left,
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun TransactionViewPreview() {
    SafeScreenContainerTest {
        TransactionView(
            transactionModel = TransactionModel(
                description = "Category",
                amount = "10",
                type = TransactionType.EXPENSE,
                transactionDate = "2026-10-08T07:53:58.813394Z"
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Transaction2ViewPreview() {
    SafeScreenContainerTest {
        TransactionView(
            transactionModel = TransactionModel(
                description = "Category",
                amount = "10",
                type = TransactionType.INCOME,
                transactionDate = "2026-10-08T07:53:58.813394Z"
            )
        )
    }
}