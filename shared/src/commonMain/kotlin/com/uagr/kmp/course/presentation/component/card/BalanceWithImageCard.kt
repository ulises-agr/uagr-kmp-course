package com.uagr.kmp.course.presentation.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.component.text.TextSmall
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.mini_chart
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun BalanceWithImageCard(
    modifierCard: Modifier = Modifier,
    modifierColum: Modifier = Modifier,
    title: String = "",
    totalBalance: String = "",
    subtitle: String = "",
    image: DrawableResource
) {
    SimpleCard(
        modifierCard = modifierCard,
        modifierColumn = modifierColum,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {
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
                TextBigBold(
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = Dimens.textSizeBigExtra,
                    color = AppTheme.colors.backgrounds.white,
                    text = totalBalance,
                    textAlign = TextAlign.Left,
                )
                TextSmall(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Gray,
                    text = subtitle,
                    textAlign = TextAlign.Left,
                )
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                        .height(100.dp),
                    contentScale = ContentScale.Fit
                )

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BalanceWithImageCardPreview() {
    SafeScreenContainerTest {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = Dimens.padding16),
            verticalArrangement = Arrangement.spacedBy(space = Dimens.padding16),
        ) {
            BalanceWithImageCard(
                title = "",
                totalBalance = "$--",
                subtitle = "Balance disponible",
                image = Res.drawable.mini_chart,
            )
        }
    }
}