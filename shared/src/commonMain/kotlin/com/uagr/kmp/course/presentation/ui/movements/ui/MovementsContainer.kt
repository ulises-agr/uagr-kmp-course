package com.uagr.kmp.course.presentation.ui.movements.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.domain.model.transaction.TransactionModel
import com.uagr.kmp.course.domain.model.transaction.TransactionType
import com.uagr.kmp.course.presentation.component.Header.BigHeader
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.transaction.CategoryChipItem
import com.uagr.kmp.course.presentation.component.transaction.CategoryChips
import com.uagr.kmp.course.presentation.component.transaction.TransactionView
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import com.uagr.kmp.course.presentation.theme.colors.backgroundBlueLight
import com.uagr.kmp.course.presentation.theme.colors.backgroundBlueSoftLight
import com.uagr.kmp.course.presentation.theme.colors.backgroundGreenSoftDark
import com.uagr.kmp.course.presentation.theme.colors.backgroundRedSoftDark
import com.uagr.kmp.course.presentation.theme.colors.textGreenDark
import com.uagr.kmp.course.presentation.theme.colors.textRedLight
import com.uagr.kmp.course.presentation.theme.colors.textWhiteDark
import course.shared.generated.resources.Res
import course.shared.generated.resources.movements_subtitle
import course.shared.generated.resources.movements_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovementsContainer(
    transactionsList: List<TransactionModel> = emptyList(),
    selectedType: TransactionType?,
    onTypeSelected: (TransactionType?) -> Unit,
    modifier: Modifier = Modifier,
    categories: List<CategoryChipItem>,
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(all = Dimens.padding16),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.height12)
    ) {
        item{
            Spacer(modifier = Modifier.fillMaxWidth())
            BigHeader(
                title = stringResource(Res.string.movements_title),
                subtitle = stringResource(Res.string.movements_subtitle),
                colorTitle = AppTheme.colors.text.black
            )
            Spacer(modifier = Modifier.height(height = Dimens.height32))
            CategoryChips(
                selectedType = selectedType,
                onTypeSelected = onTypeSelected,
                categories = categories,
            )
            Spacer(modifier = Modifier.height(height = Dimens.height32))

        }
        items(transactionsList) { transaction ->
            TransactionView(transactionModel = transaction)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MovementsContainerPreview() {
    SafeScreenContainerTest {
        val transactionCategories = listOf(
            CategoryChipItem(
                type = null,
                label = "Todos",
                backgroundColor = backgroundBlueLight,
                textColor = textWhiteDark,
            ),
            CategoryChipItem(
                type = TransactionType.INCOME,
                label = "Ingresos",
                backgroundColor = backgroundGreenSoftDark,
                textColor = textGreenDark,
            ),
            CategoryChipItem(
                type = TransactionType.EXPENSE,
                label = "Gastos",
                backgroundColor = backgroundRedSoftDark,
                textColor = textRedLight,
            ),
        )
        MovementsContainer(
            selectedType = null,
            onTypeSelected = {},
            categories = transactionCategories
        )
    }
}
