package com.uagr.kmp.course.presentation.component.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.domain.model.transaction.TransactionType
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.theme.Dimens
import com.uagr.kmp.course.presentation.theme.colors.backgroundBlueLight
import com.uagr.kmp.course.presentation.theme.colors.backgroundGreenSoftDark
import com.uagr.kmp.course.presentation.theme.colors.backgroundRedSoftDark
import com.uagr.kmp.course.presentation.theme.colors.textGreenDark
import com.uagr.kmp.course.presentation.theme.colors.textRedLight
import com.uagr.kmp.course.presentation.theme.colors.textWhiteDark

data class CategoryChipItem(
    val type: TransactionType?,
    val label: String,
    val backgroundColor: Color,
    val textColor: Color,
)

@Composable
fun CategoryChips(
    selectedType: TransactionType?,
    onTypeSelected: (TransactionType?) -> Unit,
    modifier: Modifier = Modifier,
    categories: List<CategoryChipItem>,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Dimens.padding8),

    ) {
        items(categories) { category ->
            val selected = selectedType == category.type
            val roundCorner = RoundedCornerShape(size = Dimens.height16)
            val textColor: Color = category.textColor

            FilterChip(
                selected = selected,
                onClick = {
                    onTypeSelected(category.type)
                },
                shape = roundCorner,
                label = {
                    TextBigBold(
                        fontSize = Dimens.textSizeExtraSmall,
                        color = textColor,
                        text = category.label,
                        textAlign = TextAlign.Center,
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = category.backgroundColor,
                    labelColor = textColor,
                    selectedLabelColor = category.textColor,
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TransactionTypeChipPreview(){
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
        CategoryChips(
            selectedType = TransactionType.INCOME,
            onTypeSelected = {},
            categories = transactionCategories,
        )
    }
}