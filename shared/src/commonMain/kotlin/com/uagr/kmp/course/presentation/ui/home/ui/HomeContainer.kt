package com.uagr.kmp.course.presentation.ui.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import course.shared.generated.resources.Res
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.uagr.kmp.course.domain.model.dashboard.RecentTransactionModel
import com.uagr.kmp.course.domain.model.transactions.TransactionsModel
import com.uagr.kmp.course.presentation.component.card.BalanceCard
import com.uagr.kmp.course.presentation.component.card.SmallCard
import com.uagr.kmp.course.presentation.component.card.TransactionsCard
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.component.text.TextNormal
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.everything
import course.shared.generated.resources.home_card_ahorro
import course.shared.generated.resources.home_card_gastos
import course.shared.generated.resources.home_card_ingreso
import course.shared.generated.resources.home_card_subtitle
import course.shared.generated.resources.home_name
import course.shared.generated.resources.home_title
import course.shared.generated.resources.last_translations
import course.shared.generated.resources.this_month
import org.jetbrains.compose.resources.stringResource
import kotlin.collections.List

@Composable
fun HomeContainer(
    name: String = "",
    totalBalance: String = "",
    incomeTotal: String = "",
    expensesTotal: String = "",
    monthlySavings: String = "",
    savingsRate: String = "",
    recentTransactions: List<RecentTransactionModel> = emptyList(),
){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.padding16),
        verticalArrangement = Arrangement.spacedBy(Dimens.padding16),
        horizontalAlignment = Alignment.Start,
    ) {
        item{
            Spacer(Modifier.height(Dimens.height8))
        }
        item {
            TextNormal(
                color = AppTheme.colors.text.grayBlue,
                text = stringResource(Res.string.home_name, name)
            )
        }
        item {
            TextBigBold(
                text = stringResource(Res.string.home_title),
                color = AppTheme.colors.text.black,
            )
        }
        item {
            BalanceCard(
                modifierColum = Modifier.background( AppTheme.colors.primary),
                title = "",
                totalBalance = totalBalance,
                subtitle = stringResource(Res.string.home_card_subtitle),
                incomeTitle = stringResource(Res.string.home_card_ingreso),
                incomeTotal = incomeTotal,
                expensesTitle = stringResource(Res.string.home_card_gastos),
                expensesTotal = expensesTotal
            )
        }
        item {
            TextBigBold(
                text = stringResource(Res.string.this_month),
                color = AppTheme.colors.text.black,
            )
        }
        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                SmallCard(
                    modifierCard = Modifier.weight(1f)
                        .height(150.dp)
                        .padding(Dimens.padding8),
                    title = stringResource(Res.string.home_card_gastos),
                    totalBalance = monthlySavings,
                    subtitle = "12%")
                SmallCard(
                    modifierCard = Modifier.weight(1f)
                        .height(150.dp)
                        .padding(Dimens.padding8),
                    title = stringResource(Res.string.home_card_ahorro),
                    totalBalance = savingsRate,
                    subtitle = "12%")
            }
        }
       item {
           Row(modifier = Modifier.fillMaxWidth()) {
               TextBigBold(
                   text = stringResource(Res.string.last_translations),
                   fontSize = Dimens.textSizeBig,
                   color = AppTheme.colors.text.black,
               )
               TextButton(
                   onClick = {},
                   colors = ButtonDefaults.textButtonColors(
                       contentColor =  AppTheme.colors.text.lightBlue,
                   ),
               ){
                   Text(
                       text = stringResource(Res.string.everything),
                       fontSize = Dimens.textSizeNormal
                   )
               }
           }
       }
        items(recentTransactions.size){ index ->
            TransactionsCard(
                modifier = Modifier.fillMaxWidth()
                    .padding(Dimens.height8),
                titleTransaction = recentTransactions[index].description,
                subtitleTransaction = recentTransactions[index].transaction_date,
                quantityText = recentTransactions[index].amount,
                type = recentTransactions[index].type
            )
        }
    }
}


@Preview

@Composable
private fun PreviewHomeContainer(){
    SafeScreenContainerTest{
        HomeContainer(
            name = "Panchito Perez",
            totalBalance = "$500.00",
            incomeTotal = "+ 18,5000",
            expensesTotal = "-$8,460",
            monthlySavings = "222222",
            savingsRate = "333333"
        )
    }
}