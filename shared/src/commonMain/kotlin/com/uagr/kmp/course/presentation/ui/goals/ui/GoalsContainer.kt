package com.uagr.kmp.course.presentation.ui.goals.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.domain.model.goals.GoalsDataModel
import com.uagr.kmp.course.presentation.component.Header.BigHeader
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.component.text.TextNormal
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import course.shared.generated.resources.Res
import course.shared.generated.resources.budget_subtitle
import course.shared.generated.resources.budget_title
import course.shared.generated.resources.goals_subtitle
import course.shared.generated.resources.goals_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun GoalsContainer(
    goalsData: GoalsDataModel? = null,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Dimens.padding16)
            .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxWidth())
        BigHeader(
            title = stringResource(Res.string.goals_title),
            subtitle = stringResource(Res.string.goals_subtitle),
        )
        Spacer(modifier = Modifier.height(height = Dimens.height32))

        TextNormal(
            modifier = Modifier.fillMaxWidth(),
            color = AppTheme.colors.backgrounds.black,
            text = goalsData?.description.takeIf { !it.isNullOrBlank() } ?: "Metas de la aplicación",
            textAlign = TextAlign.Left,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GoalsContainerPreview() {
    SafeScreenContainerTest {
        GoalsContainer()
    }
}
