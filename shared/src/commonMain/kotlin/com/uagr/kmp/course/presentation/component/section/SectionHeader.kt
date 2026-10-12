
package com.uagr.kmp.course.presentation.component.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
fun SectionHeader(
    title: String,
    actionText: String,
    onActionClick: () -> Unit,
    modifier: Modifier = Modifier,
    actionColor: Color = AppTheme.colors.backgrounds.actionBlue
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = actionText,
            color = actionColor,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.clickable {
                onActionClick()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SectionHeaderPreview() {
    SafeScreenContainerTest {
        SectionHeader(
            title = "Últimos movimientos",
            actionText = "Ver todos",
            onActionClick = {}
        )
    }
}
