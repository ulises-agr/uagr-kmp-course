/*
 * Button.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.uagr.kmp.course.component.text.TextNormalBold
import com.uagr.kmp.course.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.example
import course.shared.generated.resources.ic_example
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(size = Dimens.corner48),
    height: Dp = Dimens.height40,
    enabled: Boolean = true,
    backgroundColor: Color,
    textColors: Color,
    text: String,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
    ) {
        TextNormalBold(
            modifier = Modifier.fillMaxWidth(),
            color = textColors,
            text = text,
        )
    }
}

@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(size = Dimens.corner48),
    height: Dp = Dimens.height40,
    enabled: Boolean = true,
    backgroundColor: Color,
    textColors: Color,
    text: StringResource,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
    ) {
        TextNormalBold(
            modifier = Modifier.fillMaxWidth(),
            color = textColors,
            text = stringResource(text),
        )
    }
}

@Composable
fun ButtonSecondary(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(size = Dimens.corner48),
    height: Dp = Dimens.height40,
    enabled: Boolean = true,
    backgroundColor: Color,
    textColors: Color,
    text: String,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
    ) {
        TextNormalBold(
            modifier = Modifier.fillMaxWidth(),
            color = textColors,
            text = text,
        )
    }
}

@Composable
fun ButtonSecondary(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(size = Dimens.corner48),
    height: Dp = Dimens.height40,
    enabled: Boolean = true,
    backgroundColor: Color,
    textColors: Color,
    text: StringResource,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
    ) {
        TextNormalBold(
            modifier = Modifier.fillMaxWidth(),
            color = textColors,
            text = stringResource(text),
        )
    }
}

@Composable
fun ButtonOutlined(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(size = Dimens.corner48),
    height: Dp = Dimens.height40,
    enabled: Boolean = true,
    backgroundColor: Color,
    borderColor: Color,
    borderWidth: Dp = Dimens.border2,
    textColors: Color,
    text: String,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            disabledContainerColor = Color.Transparent,
        ),
        border = BorderStroke(width = borderWidth, color = borderColor),
    ) {
        TextNormalBold(
            modifier = Modifier.fillMaxWidth(),
            color = textColors,
            text = text,
        )
    }
}

@Composable
fun ButtonOutlined(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(size = Dimens.corner48),
    height: Dp = Dimens.height40,
    enabled: Boolean = true,
    backgroundColor: Color,
    borderColor: Color,
    borderWidth: Dp = Dimens.border2,
    textColors: Color,
    text: StringResource,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            disabledContainerColor = Color.Transparent,
        ),
        border = BorderStroke(width = borderWidth, color = borderColor),
    ) {
        TextNormalBold(
            modifier = Modifier.fillMaxWidth(),
            color = textColors,
            text = stringResource(text),
        )
    }
}

@Composable
fun CircularIconButton(
    modifier: Modifier = Modifier,
    size: Dp = Dimens.height24,
    backgroundColor: Color,
    iconColor: Color,
    icon: DrawableResource,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color = backgroundColor)
            .minimumInteractiveComponentSize()
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier.size(size),
            painter = painterResource(resource = icon),
            tint = iconColor,
            contentDescription = null,
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun ButtonPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Dimens.padding16),
        verticalArrangement = Arrangement.spacedBy(Dimens.padding16),
    ) {
        ButtonPrimary(
            backgroundColor = Color.Black,
            textColors = Color.White,
            text = Res.string.example,
        )
        ButtonSecondary(
            backgroundColor = Color.Gray,
            textColors = Color.White,
            text = Res.string.example,
        )
        ButtonOutlined(
            backgroundColor = Color.White,
            borderColor = Color.Black,
            textColors = Color.Black,
            text = Res.string.example,
        )
        CircularIconButton(
            backgroundColor = Color.Black,
            iconColor = Color.White,
            icon = Res.drawable.ic_example,
        )
    }
}
