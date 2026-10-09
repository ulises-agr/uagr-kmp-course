package com.uagr.kmp.course.presentation.ui.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
import com.uagr.kmp.course.domain.model.summary.SummaryModel
import com.uagr.kmp.course.domain.model.transaction.TransactionModel
import com.uagr.kmp.course.domain.model.transaction.TransactionType
import com.uagr.kmp.course.presentation.component.Header.BigHeaderInverted
import com.uagr.kmp.course.presentation.component.Header.HeaderSections
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.summary.SummaryChipView
import com.uagr.kmp.course.presentation.component.summary.SummaryView
import com.uagr.kmp.course.presentation.component.transaction.TransactionView
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.home_subtitle
import course.shared.generated.resources.home_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeContainer(
    name: String = "",
    dashBoardModel: DashboardModel = DashboardModel(),
    onSeeAllClick: () -> Unit = {},
) {
    val scrollState = rememberScrollState()
    val incomeEntity = SummaryModel(
        title = "Ingresos",
        amount = dashBoardModel.monthlyIncome
    )
    val expenseEntity = SummaryModel(
        title = "Gastos",
        amount = dashBoardModel.monthlyExpenses
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Dimens.padding16)
            .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val title: String = stringResource(Res.string.home_title).replace(
            oldValue = "@name",
            newValue = name
        )
        Spacer(modifier = Modifier.fillMaxWidth())
        BigHeaderInverted(
            title = title,
            subtitle = stringResource(Res.string.home_subtitle),
            colorTitle = AppTheme.colors.text.gray,
            colorSubtitle = AppTheme.colors.text.black,
        )
        Spacer(modifier = Modifier.height(height = Dimens.height32))

        SummaryView(
            textTitle = "Balance Total",
            textAmount = dashBoardModel.totalBalance,
            incomeEntity = incomeEntity,
            expenseEntity,
        )
        Spacer(modifier = Modifier.height(height = Dimens.height32))
        HeaderSections(
            modifier = Modifier,
            title = "Este mes"
        )
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(top = Dimens.padding16)
        ) {
            SummaryChipView(
                modifier = Modifier.weight(1f)
                    .padding(end = Dimens.padding12),
                incomeEntity = SummaryModel(
                    title = "Gastos",
                    amount = dashBoardModel.monthlyExpenses
                ),
                percentText = dashBoardModel.budgetUsage,
            )
            SummaryChipView(
                modifier = Modifier.weight(1f)
                    .padding(start = Dimens.padding12),
                incomeEntity = SummaryModel(
                    title = "Ahorros",
                    amount = dashBoardModel.monthlySavings
                ),
                percentText = dashBoardModel.savingsRate,
            )
        }
        Spacer(modifier = Modifier.height(height = Dimens.height32))
        HeaderSections(
            modifier = Modifier,
            title = "Últimos movimientos",
            subtitle = "Ver todos",
            onClick = onSeeAllClick,
        )
        Spacer(modifier = Modifier.height(height = Dimens.height16))
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(Dimens.height12)
        ) {
            dashBoardModel.recentTransactions.forEach { transaction ->
                TransactionView(transactionModel = transaction)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContainerPreview() {
    SafeScreenContainerTest {
        HomeContainer(
            name = "NAME",
            dashBoardModel = DashboardModel(
                totalBalance = "$100.0",
                monthlyIncome = "+$1.0",
                monthlyExpenses = "$2.0",
                monthlySavings = "$3.0",
                savingsRate = "2%",
                budgetUsage = "0%",
                recentTransactions = listOf(
                    TransactionModel(
                        categoryId = "Category",
                        transactionDate = "Date",
                        amount = "Amount",
                        type = TransactionType.INCOME
                    ),
                    TransactionModel(
                        categoryId = "Category",
                        transactionDate = "Date",
                        amount = "Amount",
                        type = TransactionType.EXPENSE
                    ),
                )
            )
        )
    }
}
