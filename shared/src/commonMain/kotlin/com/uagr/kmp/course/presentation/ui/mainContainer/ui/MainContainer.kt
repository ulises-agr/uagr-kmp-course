package com.uagr.kmp.course.presentation.ui.mainContainer.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest
import com.uagr.kmp.course.presentation.theme.Dimens
import com.uagr.kmp.course.presentation.ui.home.navigation.HomeTab
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import com.uagr.kmp.course.presentation.ui.buget.navigation.BudgetTab
import com.uagr.kmp.course.presentation.ui.goals.navigation.GoalsTab
import com.uagr.kmp.course.presentation.ui.transactions.navigation.TransactionTab
import course.shared.generated.resources.Res
import course.shared.generated.resources.icon_circle
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainContainer(){
    TabNavigator(HomeTab){
        Scaffold(
            bottomBar = {
                BottomBarNavigation()
            }
        ){ innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ){
                CurrentTab()
            }
        }
    }
}

@Composable
fun BottomBarNavigation(){
    val tabNavigator = LocalTabNavigator.current
    val tabs = listOf(
        HomeTab,
        TransactionTab,
        BudgetTab,
        GoalsTab
    )
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Dimens.padding16,
                vertical = Dimens.padding8
            )
            .clip(RoundedCornerShape(Dimens.padding16))
    ) {
        tabs.forEach { tab ->
            NavigationBarItem(
                selected = tabNavigator.current == tab,
                onClick = {
                    tabNavigator.current = tab
                },
                icon = {
                    Icon(
                        painter = tab.options.icon ?: painterResource(Res.drawable.icon_circle),
                        contentDescription = tab.options.title
                    )
                },
                label = {
                    Text(
                        text = tab.options.title,
                        fontSize = Dimens.textSizeExtraSmall
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewMainContainer(){
    SafeScreenContainerTest{
        MainContainer()
    }
}