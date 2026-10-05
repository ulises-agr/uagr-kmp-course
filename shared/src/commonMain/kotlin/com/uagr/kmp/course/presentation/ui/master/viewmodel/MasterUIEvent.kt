package com.uagr.kmp.course.presentation.ui.master.viewmodel

sealed class MasterUiEvent {
    internal data object Idle : MasterUiEvent()
    data object Success : MasterUiEvent()
}