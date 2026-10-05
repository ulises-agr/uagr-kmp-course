package com.uagr.kmp.course.presentation.ui.budget.viewmodel

import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.model.budget.BudgetDataModel
import com.uagr.kmp.course.utils.operators.StatusLoading

data class BudgetUiState(
    val isLoading: StatusLoading = StatusLoading.DISMISS_LOADING,
    val data: BudgetDataModel? = null,
    val errorDialog: ErrorDialogModel? = null,
)
