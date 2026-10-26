package com.uagr.kmp.course.presentation.ui.buget.viewModel

import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.model.budget.BudgetListResultModel
import com.uagr.kmp.course.domain.model.budget.BudgetResumeModel
import com.uagr.kmp.course.domain.model.budget.BudgetWithCategorytModel
import com.uagr.kmp.course.utils.operators.StatusLoading

data class BudgetUiState(
    val isLoading: StatusLoading = StatusLoading.DISMISS_LOADING,
    val errorDialog: ErrorDialogModel? = null,
    val data: BudgetListResultModel = BudgetListResultModel(data = emptyList()),
    val resumeModel: BudgetResumeModel = BudgetResumeModel(budget = "", spent = "", remaining = "", percentage = "")
)