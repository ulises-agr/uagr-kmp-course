package com.uagr.kmp.course.presentation.ui.master.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface MasterRoute {

    @Serializable
    data object Home : MasterRoute

    @Serializable
    data object Movements : MasterRoute

    @Serializable
    data object Budget : MasterRoute

    @Serializable
    data object Goals : MasterRoute
}