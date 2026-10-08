package com.uagr.kmp.course.presentation.ui.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.domain.model.home.HomeDataModel
import com.uagr.kmp.course.presentation.component.Header.BigHeader
import com.uagr.kmp.course.presentation.component.Header.BigHeaderInverted
import com.uagr.kmp.course.presentation.component.Header.HeaderSections
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.summaryView.SummaryEntity
import com.uagr.kmp.course.presentation.component.summaryView.SummaryHeadView
import com.uagr.kmp.course.presentation.component.summaryView.SummaryView
import com.uagr.kmp.course.presentation.component.text.TextNormal
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.budget_title
import course.shared.generated.resources.home_subtitle
import course.shared.generated.resources.home_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeContainer(
    name: String = "",
    homeData: HomeDataModel? = null,
    textAmount: String = "",
    incomeEntity: SummaryEntity = SummaryEntity(),
    expenseEntity: SummaryEntity = SummaryEntity(),
    onSeeAllClick: () -> Unit = {},
) {
    val scrollState = rememberScrollState()

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
            textTitle = stringResource(Res.string.home_title),
            textAmount = textAmount,
            incomeEntity = incomeEntity,
            expenseEntity,
        )
        Spacer(modifier = Modifier.height(height = Dimens.height32))
        HeaderSections(modifier = Modifier, title = "Este mes")
        Spacer(modifier = Modifier.height(height = Dimens.height32))
        HeaderSections(modifier = Modifier, title = "Últimos movimientos", subtitle = "Ver todos")
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContainerPreview() {
    SafeScreenContainerTest {
        HomeContainer(
            textAmount = "$0.00",
            incomeEntity = SummaryEntity("Ingresos", amount = "$0.0"),
            expenseEntity = SummaryEntity("Gastos", amount = "$0.0")
        )
    }
}
