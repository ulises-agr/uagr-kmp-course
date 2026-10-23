package com.uagr.kmp.course.presentation.component.container

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.uagr.kmp.course.presentation.component.text.TextBigBold
import com.uagr.kmp.course.presentation.component.text.TextNormal
import com.uagr.kmp.course.presentation.component.text.TextNormalBold
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens

@Composable
fun HeaderContainer(
    modifier: Modifier = Modifier,
    height: Dp = Dimens.height64,
    titleColor: Color = Color.Black,
    subTitleColor: Color = Color.Black,
    titleText: String = "",
    subTitleText: String = "",
) {
    Column(
        modifier = modifier
        .height(height,
            )) {
        TextBigBold(
            text = titleText,
            modifier = Modifier.align(Alignment.Start),
            color = titleColor,
        )
        TextNormal(
            modifier = Modifier.align(Alignment.Start),
            color = subTitleColor,
            text = subTitleText,
        )
    }
}
@Preview

@Composable
fun PreviewHeaderContainer(){
    SafeScreenContainerTest {
        HeaderContainer(
            modifier = Modifier.fillMaxWidth(),
        height = Dimens.height64,
        titleColor = Color.Black,
            subTitleColor = Color.Black,
        titleText = "Presupuestos",
        subTitleText = "Septiembre")
    }
}