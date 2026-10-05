package com.uagr.kmp.course.presentation.ui.master.viewmodel

import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.utils.operators.StatusLoading

data class MasterUiState(
    val isLoading: StatusLoading = StatusLoading.DISMISS_LOADING,
    //val data: MasterDataModel? = null,
    val errorDialog: ErrorDialogModel? = null,
    val currentTab: MasterTabs = MasterTabs.HOME,
    val tabs: List<MasterTabs> = MasterTabs.entries,
)
