/*
// HomeContainer.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.card.CardMainBalance
import com.uagr.kmp.course.presentation.component.card.CardTypeBalance
import com.uagr.kmp.course.domain.model.base.TransactionItemModel
import com.uagr.kmp.course.presentation.component.list.TransactionList
import com.uagr.kmp.course.presentation.component.text.TextMediumBold
import com.uagr.kmp.course.presentation.component.text.TextNormalBold
import com.uagr.kmp.course.presentation.component.text.TextNormalMedium
import com.uagr.kmp.course.presentation.component.text.TextSmallExtra
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import com.uagr.kmp.course.presentation.component.mock.homeDataMock
import com.uagr.kmp.course.presentation.ui.home.viewmodel.HomeUiState
import com.uagr.kmp.course.presentation.ui.menu.ui.MenuContainer
import com.uagr.kmp.course.presentation.ui.menu.viewmodel.MenuUiState
import course.shared.generated.resources.Res
import course.shared.generated.resources.home_title
import course.shared.generated.resources.home_welcome
import course.shared.generated.resources.home_welcome_user
import course.shared.generated.resources.home_balance_total
import course.shared.generated.resources.home_income
import course.shared.generated.resources.home_expenses
import course.shared.generated.resources.home_savings
import course.shared.generated.resources.home_period
import course.shared.generated.resources.home_transactions
import course.shared.generated.resources.home_view_all
import course.shared.generated.resources.home_empty_transactions
import course.shared.generated.resources.home_budget_usage
import course.shared.generated.resources.home_savings_rate
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeContainer(
    uiState: HomeUiState,
    onViewAllClick: () -> Unit = {},
    onOptionsClick: (() -> Unit)? = null,
    onTransactionClick: ((TransactionItemModel) -> Unit)? = null,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = Dimens.padding24)
                .verticalScroll(state = scrollState),
            horizontalAlignment = Alignment.Start,
        ) {
            Spacer(modifier = Modifier.height(Dimens.height16))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                TextNormalMedium(
                    modifier = Modifier.weight(1f),
                    color = AppTheme.colors.text.gray,
                    text = if (uiState.userName.isBlank()) {
                        stringResource(Res.string.home_welcome)
                    } else {
                        stringResource(Res.string.home_welcome_user, uiState.userName)
                    },
                    textAlign = TextAlign.Start,
                )
            }
            TextMediumBold(
                modifier = Modifier.fillMaxWidth(),
                color = AppTheme.colors.text.black,
                text = stringResource(Res.string.home_title),
                textAlign = TextAlign.Start,
            )
            Spacer(Modifier.height(Dimens.height20))
            CardMainBalance(
                balanceText = stringResource(Res.string.home_balance_total),
                balanceAmount = uiState.totalBalance,
                incomeText = stringResource(Res.string.home_income),
                incomeAmount = uiState.incomeAmount,
                expensesText = stringResource(Res.string.home_expenses),
                expensesAmount = uiState.expensesAmount,
            )
            Spacer(Modifier.height(Dimens.height24))
            TextNormalBold(
                text = stringResource(Res.string.home_period),
                color = AppTheme.colors.text.black,
                textAlign = TextAlign.Start,
            )
            Spacer(Modifier.height(Dimens.height12))
            Row(
                modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.padding16)
            ) {
                CardTypeBalance(
                    modifier = Modifier.weight(1f),
                    title = stringResource(Res.string.home_expenses),
                    amount = uiState.monthlyExpensesAmount,
                    changeText = stringResource(Res.string.home_budget_usage, uiState.expensesChange),
                    isIncreasing = uiState.expensesIncreasing,
                    isPositiveChange = uiState.expensesPositiveChange,
                    showChangeDirection = false,
                )
                CardTypeBalance(
                    modifier = Modifier.weight(1f),
                    title = stringResource(Res.string.home_savings),
                    amount = uiState.savingsAmount,
                    changeText = stringResource(Res.string.home_savings_rate, uiState.savingsChange),
                    isIncreasing = uiState.savingsIncreasing,
                    isPositiveChange = uiState.savingsPositiveChange,
                    showChangeDirection = false,
                )
            }
            Spacer(Modifier.height(Dimens.height24))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                TextNormalBold(
                    modifier = Modifier.weight(1f),
                    text = stringResource(Res.string.home_transactions),
                    color = AppTheme.colors.text.black,
                    textAlign = TextAlign.Start,
                )
                TextButton(
                    onClick = onViewAllClick,
                    contentPadding = PaddingValues(start = Dimens.padding8, end = Dimens.paddingNome),
                ) {
                    TextSmallExtra(
                        text = stringResource(Res.string.home_view_all),
                        color = AppTheme.colors.text.blue,
                        textAlign = TextAlign.End,
                    )
                }
            }
            if (uiState.transactions.isEmpty()) {
                TextSmallExtra(
                    text = stringResource(Res.string.home_empty_transactions),
                    color = AppTheme.colors.text.gray,
                    textAlign = TextAlign.Start,
                )
            } else {
                TransactionList(
                    transactions = uiState.transactions,
                    onTransactionClick = onTransactionClick,
                )
            }
            Spacer(Modifier.height(Dimens.height16))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeContainerPreview() {
    SafeScreenContainerTest {
        MenuContainer(uiState = MenuUiState(), onMenuSelected = {}) {
            HomeContainer(uiState = homeDataMock)
        }
    }
}
