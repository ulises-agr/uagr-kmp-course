/*
 * PackageViewModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.packages.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.presentation.welcome.viewmodel.PackageUiEvent
import com.tarifchakder.ktoast.ToastDuration
import com.tarifchakder.ktoast.ToastState
import com.uagr.kmp.course.domain.model.packages.PackageDataModel
import com.uagr.kmp.course.domain.model.packages.PackageModel
import com.uagr.kmp.course.domain.usecase.packages.ClearAndInsertPackagesUseCase
import com.uagr.kmp.course.domain.usecase.packages.GetLocalPackagesUseCase
import com.uagr.kmp.course.domain.usecase.packages.GetNetworkPackagesUseCase
import com.uagr.kmp.course.utils.constants.NetworkUrl
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.operators.StatusLoading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class PackageViewModel(
    private val getNetworkPackagesUseCase: GetNetworkPackagesUseCase,
    private val clearAndInsertPackagesUseCase: ClearAndInsertPackagesUseCase,
    private val getLocalPackagesUseCase: GetLocalPackagesUseCase,
): ViewModel() {
    
    private var _packageUiState = MutableStateFlow(PackageUiState())
    val packageUiState: StateFlow<PackageUiState> = _packageUiState.asStateFlow()
    
    private var _packageUiEvent = MutableStateFlow<PackageUiEvent>(PackageUiEvent.Idle)
    val packageUiEvent: StateFlow<PackageUiEvent> = _packageUiEvent.asStateFlow()
    
    init {
        getPackages()
    }
    
    private fun getPackages() = viewModelScope.launch {
        getNetworkPackagesUseCase(url = NetworkUrl.GET_PACKAGES_ENDPOINT)
            .onStart {
                _packageUiState.update { state -> state.copy(showLoader = StatusLoading.SHOW_LOADING) }
            }.catch {
                _packageUiState.update { state -> state.copy(showLoader = StatusLoading.HIDE_LOADING, showToast = true) }
                _packageUiEvent.emit(PackageUiEvent.ShowErrorDialog(message = "Error en el servicio"))
            }.collect { result ->
                when(result){
                    is NetworkResult.Success -> {
                        clearAndInsertPackages(packages = result.response.data)
                    }
                    is NetworkResult.Error -> {
                        _packageUiState.update { state -> state.copy(showLoader = StatusLoading.HIDE_LOADING) }
                        _packageUiEvent.emit(PackageUiEvent.ShowErrorDialog(message = result.message))
                    }
                }
            }
    }
    
    private fun clearAndInsertPackages(packages: List<PackageDataModel>) = viewModelScope.launch{
        clearAndInsertPackagesUseCase(packages = packages)
            .catch {
                _packageUiState.update { state -> state.copy(showLoader = StatusLoading.HIDE_LOADING, showToast = true) }
                _packageUiEvent.emit(PackageUiEvent.ShowErrorDialog(message = "Error en el servicio"))
            }
            .collect {
                getLocalPackages()
            }
    }
    
    private fun getLocalPackages() = viewModelScope.launch{
        getLocalPackagesUseCase()
            .catch {
                _packageUiState.update { state -> state.copy(showLoader = StatusLoading.HIDE_LOADING, showToast = true) }
                _packageUiEvent.emit(PackageUiEvent.ShowErrorDialog(message = "Error en el servicio"))
            }
            .collect { result ->
                _packageUiState.update { state -> state.copy(showLoader = StatusLoading.HIDE_LOADING, packages = result) }
            }
    }
    
    fun onClickButton() = viewModelScope.launch{
    
    }
    
    fun onClickIcon() = viewModelScope.launch{
        _packageUiState.update { state -> state.copy(showToast = true) }
    }
    
    fun showToast(toastState: ToastState, toastMessage : String) = viewModelScope.launch {
        toastState.showToast(message = toastMessage, duration = ToastDuration.Short)
    }
}
