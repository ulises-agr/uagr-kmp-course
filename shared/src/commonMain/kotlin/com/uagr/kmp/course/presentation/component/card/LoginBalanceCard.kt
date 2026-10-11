package com.uagr.kmp.course.presentation.component.card

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.login_balance_available
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginBalanceCard(
    modifier: Modifier = Modifier
) {
    BlueCard(
        modifier = modifier
    ) {
        Text(
            text = "\$--",
            color = AppTheme.colors.text.white,
            fontSize = Dimens.textSizeLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = stringResource(Res.string.login_balance_available),
            color = AppTheme.colors.text.white,
            fontSize = Dimens.textSizeNormal
        )

        Spacer(
            modifier = Modifier.height(Dimens.height20)
        )

        BalanceChart()
    }
}