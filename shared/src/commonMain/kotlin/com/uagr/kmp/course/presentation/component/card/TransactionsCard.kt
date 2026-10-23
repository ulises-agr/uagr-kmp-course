package com.uagr.kmp.course.presentation.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.domain.model.transactions.TransactionType
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextNormal
import com.uagr.kmp.course.presentation.component.text.TextSmall
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.icon_ingreso
import course.shared.generated.resources.icono_gastos
import org.jetbrains.compose.resources.painterResource

@Composable
fun TransactionsCard(
    modifier: Modifier = Modifier,
    titleTransaction: String = "",
    subtitleTransaction: String = "",
    quantityText: String = "",
    type: TransactionType = TransactionType.INCOME
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ){
        Image(
            painter = if (type == TransactionType.INCOME)  painterResource(Res.drawable.icon_ingreso) else painterResource(Res.drawable.icono_gastos) ,
            contentDescription = null,
            modifier = Modifier
                .width(Dimens.width42)
                .height(Dimens.width42)
        )
        Spacer(Modifier.weight(0.1f))
        Column (
            modifier = Modifier.weight(1f)
        ){
            TextSmall(
                color = AppTheme.colors.text.black,
                text = titleTransaction,
                textAlign = TextAlign.Left,
                fontSize = Dimens.textSizeExtraSmall,
            )
            TextNormal(
                color = AppTheme.colors.text.grayBlue,
                text = subtitleTransaction,
                textAlign = TextAlign.Left,
                fontSize = Dimens.textSizeExtraSmall,
            )
        }
        Spacer(Modifier.weight(1f))
        TextSmall(
            color = if (type == TransactionType.INCOME) AppTheme.colors.text.redColor else AppTheme.colors.text.greenColor,
            text = quantityText,
            textAlign = TextAlign.Left,
            fontSize = Dimens.textSizeExtraSmall,
        )
        Spacer(Modifier.weight(0.1f))
    }
}
@Preview(
    showBackground = true
)
@Composable
private fun PreviewMovementsCard() {
    SafeScreenContainerTest {
        TransactionsCard(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.1f)
                .padding(Dimens.padding16),
            titleTransaction = "Supermercado",
            subtitleTransaction = "Alimentacion",
            quantityText = "-$860",
            type = TransactionType.INCOME
        )
    }
}