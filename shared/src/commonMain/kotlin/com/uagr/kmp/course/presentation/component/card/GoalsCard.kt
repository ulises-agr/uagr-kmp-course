package com.uagr.kmp.course.presentation.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.component.text.TextMediumBold
import com.uagr.kmp.course.presentation.component.text.TextNormal
import com.uagr.kmp.course.presentation.component.text.TextNormalBold
import com.uagr.kmp.course.presentation.component.text.TextSmall
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun GoalsCard(
    modifierCard: Modifier = Modifier,
    modifierColum: Modifier = Modifier,
    title: String = "",
    totalBalance: String = "",
    porcentage: String = "",
    moneyMiss: String = "",
    goalDate: String = "",
    progress: Float,
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
                TextNormalBold(
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = Dimens.textSizeMedium,
                    color = AppTheme.colors.backgrounds.white,
                    text = title,
                    textAlign = TextAlign.Left,
                )
                Spacer(Modifier.height(Dimens.height8))


                Row (
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Column (
                        modifier = Modifier.weight(2f)
                    ){
                        TextSmall(
                            modifier = Modifier.fillMaxWidth(),
                            color = AppTheme.colors.text.lightSuperBlue,
                            text = totalBalance,
                            textAlign = TextAlign.Left,
                        )

                    }
                    Spacer(Modifier.height(Dimens.width32))

                    Column(
                        Modifier.weight(0.5f)
                    ){
                        TextNormalBold(
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = Dimens.textSizeMedium,
                            color = AppTheme.colors.backgrounds.white,
                            text = porcentage,
                            textAlign = TextAlign.Left,
                        )
                    }
                }
                Spacer(Modifier.height(Dimens.height8))
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxWidth(),
                    color = AppTheme.colors.text.lightBlue,
                    trackColor = AppTheme.colors.text.blueDark,
                )
                Spacer(Modifier.height(Dimens.height8))
                TextSmall(
                    modifier = Modifier.fillMaxWidth(),
                    color = AppTheme.colors.text.lightSuperBlue,
                    text = moneyMiss,
                    textAlign = TextAlign.Left,
                )
                Spacer(Modifier.height(Dimens.height8))

                TextSmall(
                    modifier = Modifier.fillMaxWidth(),
                    color = AppTheme.colors.text.lightSuperBlue,
                    text = goalDate,
                    textAlign = TextAlign.Left,
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GoalsCardPreview() {
    SafeScreenContainerTest {
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
}

