package com.uagr.kmp.course.presentation.ui.transactions.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uagr.kmp.course.domain.model.transactions.TransactionsModel
import com.uagr.kmp.course.presentation.component.card.TransactionsCard
import com.uagr.kmp.course.presentation.component.container.HeaderContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.buscar
import course.shared.generated.resources.transactions_textfield
import course.shared.generated.resources.transactions_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TransactionsContainer(
    month_title: String = "",
    transactions: List<TransactionsModel> = emptyList(),
    ){
    var searchText by remember {
        mutableStateOf("")
    }
    val filters = listOf(
        "Todos",
        "Ingresos",
        "Gastos"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.padding16),
        verticalArrangement = Arrangement.spacedBy(Dimens.padding16),
        horizontalAlignment = Alignment.Start,
    ) {
        Spacer(Modifier.height(Dimens.height28))
        HeaderContainer(
            modifier = Modifier.fillMaxWidth(),
            height = Dimens.height64,
            titleColor = Color.Black,
            subTitleColor = AppTheme.colors.text.grayBlue,
            titleText = stringResource(Res.string.transactions_title),
            subTitleText = month_title
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(filters.size) { filter ->
                Button(
                    onClick = {
                        // Buscar/filtrar por filter
                    }
                ) {
                    Text(text = filters.get(filter))
                }
            }
        }
        TextField(
            value = searchText,
            onValueChange = { newText ->
                searchText = newText
            },
            placeholder = {
                Text(stringResource(Res.string.transactions_textfield))
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(Res.drawable.buscar),
                    contentDescription = ""
                )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
        )
        LazyColumn {
            items(transactions.size){ index ->
                TransactionsCard(
                    modifier = Modifier.fillMaxWidth()
                        .fillMaxHeight(0.3f)
                        .padding(Dimens.height8),
                    titleTransaction = transactions[index].description,
                    subtitleTransaction = "Hoy - Alimentacion",
                    quantityText = transactions[index].amount,
                   type = transactions[index].type,
                )
            }
        }
    }
}


@Preview

@Composable
private fun PreviewTransactionsContainer(){
    SafeScreenContainerTest{
        TransactionsContainer(month_title = "September")
    }
}