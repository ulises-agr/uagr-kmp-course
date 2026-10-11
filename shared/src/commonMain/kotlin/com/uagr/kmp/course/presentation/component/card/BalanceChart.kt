package com.uagr.kmp.course.presentation.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun BalanceChart(
    modifier: Modifier = Modifier,
    barHeights: List<Int> = listOf(22, 26, 32, 40, 46, 54, 62)
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.height78)
            .background(
                color = AppTheme.colors.backgrounds.mediumBlue,
                shape = RoundedCornerShape(Dimens.corner16)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.padding16),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            barHeights.forEach { height ->
                Box(
                    modifier = Modifier
                        .width(Dimens.width16)
                        .height(height.dp)
                        .background(
                            color = AppTheme.colors.backgrounds.lightBlue,
                            shape = RoundedCornerShape(Dimens.corner4)
                        )
                )
            }
        }
    }
}