package com.uagr.kmp.course.presentation.ui.buget.ui

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
import com.uagr.kmp.course.domain.model.budget.BudgetListResultModel
import com.uagr.kmp.course.domain.model.budget.BudgetResumeModel
import com.uagr.kmp.course.domain.model.budget.BudgetWithCategorytModel
import com.uagr.kmp.course.presentation.component.card.BudgetCard
import com.uagr.kmp.course.presentation.component.card.ProgressCard
import com.uagr.kmp.course.presentation.component.container.HeaderContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextNormal
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.budget_subtitle
import course.shared.generated.resources.budget_title
import course.shared.generated.resources.home_name
import io.ktor.websocket.Frame
import org.jetbrains.compose.resources.stringResource

@Composable
fun BudgetContainer(
    month: String = "",
    data: BudgetListResultModel,
    sumaBudgets: BudgetResumeModel
){
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = Dimens.padding16),
        verticalArrangement = Arrangement.spacedBy(Dimens.padding16),
        horizontalAlignment = Alignment.Start,) {
        item {  Spacer(Modifier.height(Dimens.height28)) }
        item {
            HeaderContainer(
                modifier = Modifier.fillMaxWidth(),
                height = Dimens.height64,
                titleColor = Color.Black,
                subTitleColor = AppTheme.colors.text.grayBlue,
                titleText = stringResource(Res.string.budget_title),
                subTitleText = month) }
        item {
            BudgetCard(
                title = "Presupueto mensual",
                subtitle = sumaBudgets.budget,
                currentAmount = "Usado ${sumaBudgets.spent}",
                totalAmount = "$4,000",
                progress = 0.8f ,
                sizetitle = Dimens.textSizeSmall,
                sizeSubtitle = Dimens.textSizeBig,
                currentAmountSize = Dimens.textSizeExtraSmall,
                percentageSize = Dimens.textSizeNormal,
                modifier = Modifier,
            )
        }
        item{
            Spacer(Modifier.height(Dimens.height12))
        }
        item {
            TextNormal(
                color = AppTheme.colors.text.black,
                fontSize = Dimens.textSizeMedium,
                text = stringResource(Res.string.budget_subtitle)
            )
        }
        item{
            Spacer(Modifier.height(Dimens.height8))
        }
        items(data.data.size){ index ->
            ProgressCard(
                subtitle = data.data[index].id,
                currentAmount = data.data[index].amount,
                totalAmount = data.data[index].period,
                progress = 0.0f ,
                sizeSubtitle = Dimens.textSizeNormal,
                currentAmountSize = Dimens.textSizeExtraSmall,
                percentageSize = Dimens.textSizeNormal,
            )
        }
    }
}


@Preview

@Composable
private fun PreviewHomeContainer(){
    SafeScreenContainerTest{
        BudgetContainer(
            month = "Septiembre",
            data = BudgetListResultModel(
                data = emptyList()
            ),
            sumaBudgets = BudgetResumeModel(
                budget = "798000",
                spent = "50.00",
                remaining = "17500",
                percentage = "2.78"
            )
        )
    }
}