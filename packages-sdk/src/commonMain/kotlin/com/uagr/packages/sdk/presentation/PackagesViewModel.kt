/*
 * PackagesViewModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.packages.sdk.config.PackagesSdkConfig
import com.uagr.packages.sdk.domain.model.PackagesDataModel
import com.uagr.packages.sdk.domain.usecase.ClearAndInsertPackagesUseCase
import com.uagr.packages.sdk.domain.usecase.GetLocalPackagesUseCase
import com.uagr.packages.sdk.domain.usecase.GetNetworkPackagesUseCase
import com.uagr.packages.sdk.internal.network.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PackagesViewModel(
    private val config: PackagesSdkConfig,
    private val getNetworkPackagesUseCase: GetNetworkPackagesUseCase,
    private val clearAndInsertPackagesUseCase: ClearAndInsertPackagesUseCase,
    private val getLocalPackagesUseCase: GetLocalPackagesUseCase,
) : ViewModel() {

    private val _packagesUiState = MutableStateFlow(PackagesUiState())
    val packagesUiState: StateFlow<PackagesUiState> = _packagesUiState.asStateFlow()

    private val _packagesUiEvent = MutableStateFlow<PackagesUiEvent>(PackagesUiEvent.Idle)
    val packagesUiEvent: StateFlow<PackagesUiEvent> = _packagesUiEvent.asStateFlow()

    init {
        refreshPackages()
    }

    fun refreshPackages() = viewModelScope.launch {
        getNetworkPackagesUseCase(url = config.productsUrl)
            .onStart {
                _packagesUiState.update { state ->
                    state.copy(isLoading = PackagesLoadingStatus.SHOW_LOADING)
                }
            }.catch {
                _packagesUiState.update { state ->
                    state.copy(isLoading = PackagesLoadingStatus.DISMISS_LOADING)
                }
                _packagesUiEvent.emit(PackagesUiEvent.ShowError(message = GENERIC_ERROR))
            }.collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        clearAndInsertPackages(packages = result.response.data)
                    }
                    is NetworkResult.Error -> {
                        _packagesUiState.update { state ->
                            state.copy(isLoading = PackagesLoadingStatus.DISMISS_LOADING)
                        }
                        _packagesUiEvent.emit(PackagesUiEvent.ShowError(message = result.message))
                    }
                }
            }
    }

    private fun clearAndInsertPackages(packages: List<PackagesDataModel>) = viewModelScope.launch {
        clearAndInsertPackagesUseCase(packages = packages)
            .catch {
                _packagesUiState.update { state ->
                    state.copy(isLoading = PackagesLoadingStatus.DISMISS_LOADING)
                }
                _packagesUiEvent.emit(PackagesUiEvent.ShowError(message = GENERIC_ERROR))
            }.collect {
                getLocalPackages()
            }
    }

    private fun getLocalPackages() = viewModelScope.launch {
        getLocalPackagesUseCase()
            .catch {
                _packagesUiState.update { state ->
                    state.copy(isLoading = PackagesLoadingStatus.DISMISS_LOADING)
                }
                _packagesUiEvent.emit(PackagesUiEvent.ShowError(message = GENERIC_ERROR))
            }.collect { result ->
                _packagesUiState.update { state ->
                    state.copy(
                        isLoading = PackagesLoadingStatus.DISMISS_LOADING,
                        packages = result,
                    )
                }
            }
    }

    private companion object {
        const val GENERIC_ERROR = "Please try again later"
    }
}
