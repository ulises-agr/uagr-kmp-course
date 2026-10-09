package com.uagr.kmp.course.presentation.ui.home.viewmodel

import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
import com.uagr.kmp.course.domain.model.summary.SummaryModel
import com.uagr.kmp.course.utils.operators.StatusLoading

data class HomeUiState(
    val isLoading: StatusLoading = StatusLoading.DISMISS_LOADING,
    val errorDialog: ErrorDialogModel? = null,
    val name: String = "",
    val amountSummary: String = "",
    val incomeModel: SummaryModel = SummaryModel(),
    val expenseModel: SummaryModel = SummaryModel(),
    val dashBoardModel: DashboardModel = DashboardModel(),
)
