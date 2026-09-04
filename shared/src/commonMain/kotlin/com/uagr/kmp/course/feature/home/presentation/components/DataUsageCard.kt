package com.uagr.kmp.course.feature.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uagr.kmp.course.core.ui.theme.AppTheme
import com.uagr.kmp.course.core.ui.theme.Dimens

@Composable
fun DataUsageCard(
    showInfo: (Int) -> Unit = {},
    activatePackage: (Int) -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Sección Superior (Título y Icono)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Internet Full",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "Paquete: Ilimitado",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
                IconButton(
                    onClick = {
                        showInfo(1)
                    },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "Información",
                        //tint = Color(0xFFC62828), // Color rojo para el icono
                    )
                }
            }

            // Espacio entre secciones
            Spacer(modifier = Modifier.height(32.dp))

            // Sección Central (Valor y Unidad)
            Text(
                text = "580",
                style = TextStyle(
                    fontSize = 72.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
            )
            Text(
                text = "MB Usados",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            // Espacio entre secciones
            Spacer(modifier = Modifier.height(24.dp))

            // Sección de Progreso (Barra y Texto de Renovación)
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(),
               // color = AppTheme.colors.outlineVariant,
                thickness = Dimens.dp2
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Renueva en 27 Días / 5 Abril - 4 Mayo",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            // Espacio antes del botón
            Spacer(modifier = Modifier.height(32.dp))

            // Sección Inferior (Botón de Acción)
            Button(
                onClick = {
                    activatePackage(1)
                },
                modifier = Modifier.fillMaxWidth(),
                //colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC62828)), // Color rojo intenso
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "Activar Paquete",
                    style = MaterialTheme.typography.labelLarge,
                    //color = Color.White,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun DataUsageCardPreview() {
    AppTheme {
        DataUsageCard() {

        }
    }
}