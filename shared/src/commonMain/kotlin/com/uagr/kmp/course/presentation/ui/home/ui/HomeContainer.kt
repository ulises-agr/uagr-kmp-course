package com.uagr.kmp.course.presentation.ui.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.navigation.BottomNavigationBar
import com.uagr.kmp.course.presentation.component.navigation.HomeTab
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.Dimens
import com.uagr.kmp.course.presentation.ui.budget.ui.BudgetContent
import com.uagr.kmp.course.presentation.ui.goals.ui.GoalsContent
import com.uagr.kmp.course.presentation.ui.movements.ui.MovementsContent

@Composable
fun HomeContainer() {

    var selectedTab by remember {
        mutableStateOf(HomeTab.HOME)
    }

    Scaffold(
        containerColor = AppTheme.colors.backgrounds.lightGray,
        bottomBar = {
            BottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { tab -> selectedTab = tab }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(Dimens.padding16)
        ) {
            when (selectedTab) {

                HomeTab.HOME -> {
                    HomeContent(
                        onViewAllMovements = {
                            selectedTab = HomeTab.MOVEMENTS
                        }
                    )
                }

                HomeTab.MOVEMENTS -> {

                    MovementsContent()

                }


                HomeTab.BUDGET -> {

                    BudgetContent()

                }


                HomeTab.GOALS -> {

                    GoalsContent()

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeContainerPreview() {
    AppTheme {
        HomeContainer()
    }
}