package com.uagr.kmp.course.presentation.ui.movements.viewmodel

import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.model.movements.MovementsDataModel
import com.uagr.kmp.course.utils.operators.StatusLoading

data class MovementsUiState(
    val isLoading: StatusLoading = StatusLoading.DISMISS_LOADING,
    val data: MovementsDataModel? = null,
    val errorDialog: ErrorDialogModel? = null,
)
