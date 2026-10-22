package com.uagr.kmp.course.presentation.ui.goals.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.uagr.kmp.course.presentation.component.card.BlueCard
import com.uagr.kmp.course.presentation.component.card.ProgressCard
import com.uagr.kmp.course.presentation.component.section.ScreenHeader
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun GoalsContent() {
    val blue = AppTheme.colors.backgrounds.actionBlue
    val secondaryText = MaterialTheme.colorScheme.onSurfaceVariant

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.padding24)
    ) {

        ScreenHeader(
            title = "Metas de ahorro",
            subtitle = "Convierte objetivos en progreso visible."
        )

        BlueCard {
            Column(
                verticalArrangement = Arrangement.spacedBy(Dimens.padding16)
            ) {
                Text(
                    text = "Viaje a Japón",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$28,000 de $50,000",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )

                    Text(
                        text = "56%",
                        fontSize = Dimens.textSizeLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                LinearProgressIndicator(
                    progress = { 0.56f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.height8),
                    color = AppTheme.colors.backgrounds.lightBlue,
                    trackColor = Color.White.copy(alpha = 0.25f),
                    gapSize = Dimens.widthNome,
                    drawStopIndicator = {}
                )

                Spacer(modifier = Modifier.height(Dimens.height8))

                Text(
                    text = "Faltan $22,000",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )

                Text(
                    text = "Meta · Dic 2027",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )
            }
        }


        Text(
            text = "Otras metas",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )


        ProgressCard(
            title = "Fondo de emergencia",
            amount = "$12,500 / $30,000",
            percentage = "42%",
            progress = 12500f / 30000f,
            progressColor = blue
        )

        ProgressCard(
            title = "MacBook",
            amount = "$9,000 / $35,000",
            percentage = "26%",
            progress = 9000f / 35000f,
            progressColor = blue
        )



        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.height56),
            shape = RoundedCornerShape(Dimens.corner20),
            colors = ButtonDefaults.buttonColors(
                containerColor = blue.copy(alpha = 0.08f),
                contentColor = blue
            )
        ) {
            Text(
                text = "+  Nueva meta",
                fontSize = Dimens.textSizeSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}