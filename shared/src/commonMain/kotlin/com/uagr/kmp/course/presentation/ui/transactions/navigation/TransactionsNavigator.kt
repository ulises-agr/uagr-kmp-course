package com.uagr.kmp.course.presentation.ui.transactions.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.uagr.kmp.course.presentation.ui.buget.ui.BudgetScreen
import com.uagr.kmp.course.presentation.ui.transactions.ui.TransactionsScreen
import course.shared.generated.resources.Res
import course.shared.generated.resources.icon_circle
import course.shared.generated.resources.transaction_tab
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

data object TransactionTab: Tab {
    override val options: TabOptions
        @Composable
        get(){
            val title = stringResource(Res.string.transaction_tab)
            val icon = painterResource(Res.drawable.icon_circle)
            return remember(title, icon){
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content(){
        TransactionsScreen()
    }
}
