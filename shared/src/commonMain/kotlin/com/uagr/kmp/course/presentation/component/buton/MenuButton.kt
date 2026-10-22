/*
// MenuButton.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.component.buton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextSmallExtraBold
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.arrow_back
import course.shared.generated.resources.example
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MenuButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    size: Dp = Dimens.height16,
    backgroundButton: Color = AppTheme.colors.backgrounds.blueMenuSelected,
    textColor: Color = AppTheme.colors.backgrounds.blue,
    text: String = "",
    iconColor: Color = AppTheme.colors.backgrounds.blue,
    icon: DrawableResource,
    shape: Shape = RoundedCornerShape(size = Dimens.corner12),
    isSelected: Boolean = false,
) {
    Column(
        modifier = modifier
            .height(Dimens.height64)
            .clip(shape)
            .selectable(selected = isSelected, role = Role.Tab, onClick = onClick)
            .padding(vertical = Dimens.padding4),
        verticalArrangement = Arrangement.spacedBy(Dimens.padding4),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .width(Dimens.width48)
                .height(Dimens.height28)
                .clip(shape)
                .background(color = if (isSelected) backgroundButton else Color.Transparent),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                modifier = Modifier.size(size),
                tint = if (isSelected) iconColor else AppTheme.colors.text.gray,
                painter = painterResource(icon),
                contentDescription = null,
            )
        }
        TextSmallExtraBold(
            modifier = Modifier.fillMaxWidth(),
            fontSize = 10.sp,
            color = if (isSelected) textColor else AppTheme.colors.text.gray,
            text = text,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuButtonPreview() {
    SafeScreenContainerTest {
        Box(
            modifier = Modifier
                .padding(all = Dimens.padding16),
        ) {
            MenuButton(
                text = stringResource(Res.string.example),
                icon = Res.drawable.arrow_back,
            )
        }
    }
}
