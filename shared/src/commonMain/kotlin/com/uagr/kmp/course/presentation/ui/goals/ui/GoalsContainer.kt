package com.uagr.kmp.course.presentation.ui.goals.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.card.GoalsCard
import com.uagr.kmp.course.presentation.component.card.ProgressCard
import com.uagr.kmp.course.presentation.component.container.HeaderContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextNormal
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.goals_others
import course.shared.generated.resources.goals_subtitle
import course.shared.generated.resources.goals_tab
import course.shared.generated.resources.goals_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun GoalsContainer(){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.padding16),
        verticalArrangement = Arrangement.spacedBy(Dimens.padding16),
        horizontalAlignment = Alignment.Start,
    ) {
        item {
            Spacer(Modifier.height(Dimens.height28))
        }
        item {
            HeaderContainer(
                modifier = Modifier.fillMaxWidth(),
                height = Dimens.height64,
                titleColor = Color.Black,
                subTitleColor = AppTheme.colors.text.grayBlue,
                titleText = stringResource(Res.string.goals_title),
                subTitleText = stringResource(Res.string.goals_subtitle))
        }

        item {
            GoalsCard(
                modifierColum = Modifier.background(
                    AppTheme.colors.primary
                ),
                title = "Viaje a Japon",
                totalBalance = "$28,000 de $50,000",
                porcentage = "56%",
                moneyMiss = "Faltan $22,000",
                goalDate = "Meta - Dic 2027",
                progress = 0.5f,
            )
        }
        item {
            TextNormal(
                color = AppTheme.colors.text.black,
                fontSize = Dimens.textSizeMedium,
                text = stringResource(Res.string.goals_others)
            )
        }

        item(10){
            ProgressCard(
                subtitle ="Alimentación",
                currentAmount = "$3,200",
                totalAmount = "$4,000",
                progress = 0.8f ,
                sizeSubtitle = Dimens.textSizeNormal,
                currentAmountSize = Dimens.textSizeExtraSmall,
                percentageSize = Dimens.textSizeNormal,
            )
        }
    }
}
@Preview

@Composable
private fun PreviewGoalsContainer(){
    SafeScreenContainerTest{
        GoalsContainer()
    }
}