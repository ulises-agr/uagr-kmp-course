/*
// TransactionList.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.component.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.uagr.kmp.course.domain.model.base.TransactionItemModel
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.mock.transactionListMock
import com.uagr.kmp.course.presentation.component.text.TextSmall
import com.uagr.kmp.course.presentation.component.text.TextNormalBold
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

enum class TransactionType { INCOME, EXPENSE }

@Composable
fun TransactionList(
    transactions: List<TransactionItemModel>,
    modifier: Modifier = Modifier,
    onTransactionClick: ((TransactionItemModel) -> Unit)? = null,
    incomeColor: Color = AppTheme.colors.status.success,
    expenseColor: Color = AppTheme.colors.status.error,
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(Dimens.padding4)) {
        transactions.forEach { transaction ->
            TransactionItem(
                transaction = transaction,
                onClick = onTransactionClick?.let { callback -> { callback(transaction) } },
                incomeColor = incomeColor,
                expenseColor = expenseColor,
            )
        }
    }
}

@Composable
fun TransactionItem(
    transaction: TransactionItemModel,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    incomeColor: Color = AppTheme.colors.status.success,
    expenseColor: Color = AppTheme.colors.status.error,
) {
    val isIncome = transaction.type == TransactionType.INCOME
    val amountColor = if (isIncome) incomeColor else expenseColor
    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(if (onClick != null) Modifier
                .clickable(onClick = onClick) else Modifier)
            .padding(vertical = Dimens.padding8),
        horizontalArrangement = Arrangement.spacedBy(Dimens.padding12),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(Dimens.height48)
                .background(
                    color = if (isIncome) AppTheme.colors.status.successContainer else AppTheme.colors.status.errorContainer,
                    shape = RoundedCornerShape(Dimens.corner12),
            ),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                .size(Dimens.height10)
                .background(
                    color = amountColor,
                    shape = CircleShape,
                )
            )
        }
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(Dimens.padding4)
        ) {
            TextNormalBold(
                text = transaction.title,
                color = AppTheme.colors.text.black,
                textAlign = TextAlign.Start,
            )
            TextSmall(
                text = transaction.subtitle,
                color = AppTheme.colors.text.gray,
                textAlign = TextAlign.Start,
            )
        }
        TextNormalBold(
            text = transaction.amount,
            modifier = Modifier.weight(0.65f),
            color = amountColor,
            textAlign = TextAlign.End,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TransactionListPreview() {
    SafeScreenContainerTest {
        TransactionList(
            modifier = Modifier.padding(Dimens.padding16),
            transactions = transactionListMock,
        )
    }
}
