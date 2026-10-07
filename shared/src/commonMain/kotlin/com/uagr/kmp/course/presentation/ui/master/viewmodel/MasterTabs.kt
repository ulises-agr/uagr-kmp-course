package com.uagr.kmp.course.presentation.ui.master.viewmodel

enum class MasterTabs {
    HOME,
    MOVEMENTS,
    BUDGET,
    GOALS,
}


sealed interface MasterAction {
    data class SelectTab(
        val tab: MasterTabs
    ) : MasterAction
}