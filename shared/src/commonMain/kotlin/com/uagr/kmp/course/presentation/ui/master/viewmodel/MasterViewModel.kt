package com.uagr.kmp.course.presentation.ui.master.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Payments
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.presentation.component.bottomBar.NavigationBarItemModel
import course.shared.generated.resources.Res
import course.shared.generated.resources.accept
import course.shared.generated.resources.error
import course.shared.generated.resources.navigationBar_Budget
import course.shared.generated.resources.navigationBar_Goals
import course.shared.generated.resources.navigationBar_Home
import course.shared.generated.resources.navigationBar_Movements
import course.shared.generated.resources.please_try_again_later
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class MasterViewModel : ViewModel() {
    private var _masterUiState = MutableStateFlow(MasterUiState())
    val masterUiState: StateFlow<MasterUiState> = _masterUiState.asStateFlow()

    private var _masterUiEvent = MutableStateFlow<MasterUiEvent>(MasterUiEvent.Idle)
    val masterUiEvent: StateFlow<MasterUiEvent> = _masterUiEvent.asStateFlow()

    init {
        getNavigationItems<MasterTabs>()
    }

    fun updateCurrentTab(tab: MasterTabs) = viewModelScope.launch {
        _masterUiState.update { state -> state.copy(currentTab = tab) }
    }

    fun updateNavigationItems(list: List<NavigationBarItemModel<MasterTabs>>) = viewModelScope.launch {
        _masterUiState.update { state -> state.copy(navigationItems = list) }
    }

    private suspend fun setErrorDialog(message: String? = null): ErrorDialogModel =
        ErrorDialogModel(
            title = getString(resource = Res.string.error),
            message = message ?: getString(resource = Res.string.please_try_again_later),
            primaryButtonText = getString(resource = Res.string.accept),
        )

    fun dismissErrorDialog() = viewModelScope.launch {
        _masterUiState.update { state -> state.copy(errorDialog = null) }
    }

    fun resetUiEvent() = viewModelScope.launch {
        _masterUiEvent.value = MasterUiEvent.Idle
    }
}
 fun <T>MasterViewModel.getNavigationItems() = viewModelScope.launch {
    val items = listOf(
        NavigationBarItemModel(
            id = MasterTabs.HOME,
            label = getString(Res.string.navigationBar_Home),
            icon = Icons.Default.Home,
        ),
        NavigationBarItemModel(
            id = MasterTabs.MOVEMENTS,
            label = getString(Res.string.navigationBar_Movements),
            icon = Icons.Default.Payments,
        ),
        NavigationBarItemModel(
            id = MasterTabs.BUDGET,
            label = getString(Res.string.navigationBar_Budget),
            icon = Icons.Default.AccountBalanceWallet,
        ),
        NavigationBarItemModel(
            id = MasterTabs.GOALS,
            label = getString(Res.string.navigationBar_Goals),
            icon = Icons.Default.Flag,
        )
    )
    updateNavigationItems(items)
}