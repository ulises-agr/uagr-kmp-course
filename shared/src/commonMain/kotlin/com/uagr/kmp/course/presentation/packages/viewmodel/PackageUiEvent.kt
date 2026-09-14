package com.kmp.presentation.welcome.viewmodel

sealed class PackageUiEvent {
    internal data object Idle : PackageUiEvent()
    internal data class  ShowErrorDialog(val message : String) : PackageUiEvent()
}