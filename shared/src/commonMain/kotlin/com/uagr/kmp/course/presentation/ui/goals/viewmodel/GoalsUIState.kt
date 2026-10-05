package com.uagr.kmp.course.presentation.ui.goals.viewmodel

import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.model.goals.GoalsDataModel
import com.uagr.kmp.course.utils.operators.StatusLoading

data class GoalsUiState(
    val isLoading: StatusLoading = StatusLoading.DISMISS_LOADING,
    val data: GoalsDataModel? = null,
    val errorDialog: ErrorDialogModel? = null,
)
