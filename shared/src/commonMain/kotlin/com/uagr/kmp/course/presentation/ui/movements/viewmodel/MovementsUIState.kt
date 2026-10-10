package com.uagr.kmp.course.presentation.ui.movements.viewmodel

import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.model.transaction.TransactionModel
import com.uagr.kmp.course.domain.model.transaction.TransactionType
import com.uagr.kmp.course.presentation.component.transaction.CategoryChipItem
import com.uagr.kmp.course.utils.operators.StatusLoading

data class MovementsUiState(
    val isLoading: StatusLoading = StatusLoading.DISMISS_LOADING,
    val transactionsList: List<TransactionModel> = emptyList(),
    val errorDialog: ErrorDialogModel? = null,
    val selectedType: TransactionType? = null,
    val filteredTransactions: List<TransactionModel> = emptyList(),
    val categories: List<CategoryChipItem> = emptyList(),
)
