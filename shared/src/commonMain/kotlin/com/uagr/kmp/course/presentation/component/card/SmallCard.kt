package com.uagr.kmp.course.presentation.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.component.text.TextSmall
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun SmallCard(
    modifierCard: Modifier = Modifier,
    modifierColum: Modifier = Modifier,
    title: String = "",
    totalBalance: String = "",
    subtitle: String = "",
){
    SimpleCard(
        modifierCard = modifierCard,
        modifierColumn = modifierColum,
    ){
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(Dimens.height4),
        ) {
            TextSmall(
                modifier = Modifier.fillMaxWidth(),
                color = AppTheme.colors.text.lightSuperBlue,
                text = title,
                textAlign = TextAlign.Left,
            )
            Spacer(modifier = Modifier.height(Dimens.height4))
            TextBigBold(
                modifier = Modifier.fillMaxWidth(),
                fontSize = Dimens.textSizeBig,
                color = AppTheme.colors.backgrounds.black,
                text = totalBalance,
                textAlign = TextAlign.Left,
            )
            Spacer(modifier = Modifier.height(Dimens.height4))
            TextSmall(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Gray,
                text = subtitle,
                textAlign = TextAlign.Left,
            )

        }
    }
}

@Preview

@Composable
private fun PreviewSmallCard(){
    SafeScreenContainerTest {
    SmallCard(
        modifierCard = Modifier.fillMaxWidth(0.5f)
            .fillMaxHeight(0.2f)
            .padding(Dimens.padding16),
        title = "Gastos",
        totalBalance = "$8,460",
        subtitle = "12%")
    }
}