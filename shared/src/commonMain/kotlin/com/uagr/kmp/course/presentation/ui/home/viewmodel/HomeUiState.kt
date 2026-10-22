package com.uagr.kmp.course.presentation.ui.home.viewmodel

import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.model.home.DashboardModel
import com.uagr.kmp.course.utils.operators.StatusLoading

data class HomeUiState(
    val isLoading: StatusLoading = StatusLoading.DISMISS_LOADING,
    val userName: String = "Jorge",
    val dashboard: DashboardModel? = null,
    val errorDialog: ErrorDialogModel? = null,
)