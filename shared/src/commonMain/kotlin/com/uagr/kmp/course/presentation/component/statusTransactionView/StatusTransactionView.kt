package com.uagr.kmp.course.presentation.component.statusTransactionView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.domain.model.transaction.TransactionModel
import com.uagr.kmp.course.domain.model.transaction.TransactionType
import com.uagr.kmp.course.presentation.component.Header.BigHeader
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun StatusTransactionView(
    modifier: Modifier = Modifier,
    transactionType: TransactionType = TransactionType.UNKNOWN
) {
    val roundCorner = RoundedCornerShape(size = Dimens.height16)
    val backgroundColor = when (transactionType) {
        TransactionType.INCOME -> AppTheme.colors.backgrounds.greenSoft
        TransactionType.EXPENSE -> AppTheme.colors.backgrounds.redSoft
        TransactionType.UNKNOWN -> AppTheme.colors.backgrounds.white
    }

    val dotColor = when (transactionType) {
        TransactionType.INCOME -> AppTheme.colors.text.green
        TransactionType.EXPENSE -> AppTheme.colors.text.red
        TransactionType.UNKNOWN -> AppTheme.colors.text.black
    }

    Box(
        modifier = modifier
            .background(color = backgroundColor,
                shape = roundCorner)
            .size(Dimens.height48),
        contentAlignment = Alignment.Center
    ){
        Box(
            modifier = Modifier
                .background(color = dotColor,
                    shape = CircleShape)
                .size(Dimens.height8)
        ){

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun StatusTransactionViewPreview() {
    SafeScreenContainerTest {
        StatusTransactionView(
            transactionType = TransactionType.INCOME,
        )
    }
}