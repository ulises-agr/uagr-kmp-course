package com.uagr.kmp.course.component.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.component.button.Button
import com.uagr.kmp.course.component.text.TextBigBold
import com.uagr.kmp.course.component.text.TextNormal
import com.uagr.kmp.course.component.text.TextNormalBold
import com.uagr.kmp.course.component.text.TextSmallExtra
import com.uagr.kmp.course.theme.AppTheme
import com.uagr.kmp.course.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.cardButton
import course.shared.generated.resources.cardQuantity
import course.shared.generated.resources.cardQuantityUnit
import course.shared.generated.resources.cardSubtitle
import course.shared.generated.resources.cardTitle
import course.shared.generated.resources.cardUntilDate
import course.shared.generated.resources.cardUsed
import course.shared.generated.resources.ic_alert
import course.shared.generated.resources.welcomeButton
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun Card(
    modifier: Modifier = Modifier,
    colors: CardColors,
    onClickButton: () -> Unit = { },
    onClickIcon: () -> Unit = { }
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = colors,
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.padding4
        )
    ) {
        Column(
            modifier = Modifier.padding(Dimens.padding16)
        ) {
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextNormalBold(
                    color = AppTheme.colors.cards.cardsTitle,
                    text = stringResource(Res.string.cardTitle),
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    modifier = Modifier
                        .size(Dimens.height16)
                        .clickable{
                            onClickIcon()
                        },
                    painter = painterResource(Res.drawable.ic_alert),
                    tint = AppTheme.colors.button.enable,
                    contentDescription = null
                )
            }
            
            Spacer(modifier = Modifier.height(Dimens.height8))
            
            TextSmallExtra(
                color = AppTheme.colors.cards.cardsSubtitle,
                text = stringResource(Res.string.cardSubtitle),
            )
            
            Spacer(modifier = Modifier.height(Dimens.height8))
            
            TextBigBold(
                modifier = Modifier.fillMaxWidth(),
                color = AppTheme.colors.cards.cardsTitle,
                fontSize = Dimens.textSizeExtraBig,
                text = stringResource(Res.string.cardQuantity),
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextNormalBold(
                    color = AppTheme.colors.cards.cardsTitle,
                    text = stringResource(Res.string.cardQuantityUnit),
                )
                TextNormal(
                    color = AppTheme.colors.text.cardsTitle,
                    text = stringResource(Res.string.cardUsed),
                )
            }
            
            Spacer(modifier = Modifier.height(Dimens.height8))
            
            HorizontalDivider(
                modifier = modifier.height(Dimens.height2),
                color = AppTheme.colors.cards.cardDivider
            )
            
            Spacer(modifier = Modifier.height(Dimens.height8))
            
            TextSmallExtra(
                modifier = Modifier.fillMaxWidth(),
                color = AppTheme.colors.cards.cardsSubtitle,
                text = stringResource(Res.string.cardUntilDate),
            )
            
            Spacer(modifier = Modifier.height(Dimens.height16))
            
            Button(
                modifier = Modifier.fillMaxWidth(),
                fontSize = Dimens.textSizeNormal,
                color = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.colors.button.enable,
                    contentColor = AppTheme.colors.button.contentEnabled,
                    disabledContainerColor = AppTheme.colors.button.disable,
                    disabledContentColor = AppTheme.colors.button.contentDisabled
                ),
                text = stringResource(Res.string.cardButton),
                textAlign = TextAlign.Center,
                onClickButton = onClickButton
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TextPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Dimens.padding16),
        verticalArrangement = Arrangement.spacedBy(Dimens.padding16),
    ) {
        Card(
            modifier = Modifier,
            colors = CardDefaults.cardColors(
                containerColor = AppTheme.colors.backgrounds.backgroundColor,
                contentColor = AppTheme.colors.text.black,
            ),
            onClickButton = {
            
            }
        )
    }
}
