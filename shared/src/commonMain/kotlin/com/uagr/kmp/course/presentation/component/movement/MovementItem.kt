
package com.uagr.kmp.course.presentation.component.movement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun MovementItem(
    title: String,
    description: String,
    amount: String,
    color: Color,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimens.padding16)
    ) {
        Box(
            modifier = Modifier
                .size(Dimens.height48)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(Dimens.corner16)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "●",
                color = color
            )
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = description,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Text(
            text = amount,
            color = color,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MovementItemPreview() {
    SafeScreenContainerTest {
        MovementItem(
            title = "Supermercado",
            description = "Hoy · Alimentación",
            amount = "-$860",
            color = AppTheme.colors.finTrackExpense,
            backgroundColor = AppTheme.colors.finTrackExpenseBackground,
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.padding16)
        )
    }
}