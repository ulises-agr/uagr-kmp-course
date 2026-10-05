package com.uagr.kmp.course.presentation.ui.master.viewmodel

import com.uagr.kmp.course.presentation.ui.master.navigation.MasterRoute
import course.shared.generated.resources.Res
import course.shared.generated.resources.ic_example
import org.jetbrains.compose.resources.DrawableResource

enum class MasterTabs(
    val title: String,
    val route: MasterRoute,
    val icon: DrawableResource = Res.drawable.ic_example,
) {
    HOME(
        title = "Inicio",
        route = MasterRoute.Home,
    ),

    MOVEMENTS(
        title = "Movimientos",
        route = MasterRoute.Movements,
    ),

    BUDGET(
        title = "Presupuesto",
        route = MasterRoute.Budget,
    ),

    GOALS(
        title = "Metas",
        route = MasterRoute.Goals,
    ),
}
