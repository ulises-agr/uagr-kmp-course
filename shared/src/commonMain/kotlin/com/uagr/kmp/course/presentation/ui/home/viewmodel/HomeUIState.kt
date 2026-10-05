package com.uagr.kmp.course.presentation.ui.home.viewmodel

import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.model.home.HomeDataModel
import com.uagr.kmp.course.utils.operators.StatusLoading

data class HomeUiState(
    val isLoading: StatusLoading = StatusLoading.DISMISS_LOADING,
    val data: HomeDataModel? = null,
    val errorDialog: ErrorDialogModel? = null,
)
