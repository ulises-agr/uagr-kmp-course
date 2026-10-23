package com.uagr.kmp.course.presentation.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.theme.Dimens


@Composable
fun BudgetCard(
    title: String,
    subtitle: String,
    currentAmount: String,
    totalAmount: String,
    progress: Float,
    percentage: String? = null,
    modifier: Modifier = Modifier,
    sizetitle: TextUnit = Dimens.textSizeSmall,
    sizeSubtitle: TextUnit = Dimens.textSizeBig,
    currentAmountSize: TextUnit = Dimens.textSizeExtraSmall,
    percentageSize: TextUnit = Dimens.textSizeNormal,
    cardBackgroundColor: Color = Color.White,
    cardElevation: Dp = Dimens.elevation4,
){
    Card(
        modifier = modifier
            .fillMaxWidth() ,
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = cardElevation),
    ) {
        Column(

            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)

        ) {
            Text(text = title, fontSize = sizetitle)
            Spacer(Modifier.height(Dimens.height12))

            Text(text = subtitle,
                fontSize = sizeSubtitle)
            Spacer(Modifier.height(Dimens.height8))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "$currentAmount / $totalAmount",
                    fontSize = currentAmountSize
                )


                percentage?.let {
                    Text(text = it,
                        fontSize = percentageSize)
                }
            }
            Spacer(Modifier.height(Dimens.height12))

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
@Preview
@Composable
fun BudgetCardCard(){
    SafeScreenContainerTest {
        Column {
            BudgetCard(
                title = "Presupuesto mensual",
                subtitle ="$12000",
                currentAmount = "Usado $3,200",
                totalAmount = "$4,000",
                progress = 0.8f ,
                sizetitle = Dimens.textSizeSmall,
                sizeSubtitle = Dimens.textSizeBig,
                currentAmountSize = Dimens.textSizeExtraSmall,
                percentageSize = Dimens.textSizeNormal,
            )
        }
    }
}