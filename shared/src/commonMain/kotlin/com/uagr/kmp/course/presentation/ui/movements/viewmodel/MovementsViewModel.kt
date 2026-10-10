package com.uagr.kmp.course.presentation.ui.movements.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Payments
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.domain.model.transaction.TransactionModel
import com.uagr.kmp.course.domain.model.transaction.TransactionType
import com.uagr.kmp.course.domain.usecase.transactions.GetTransactionsUseCase
import com.uagr.kmp.course.domain.usecase.user.GetUserTokenUseCase
import com.uagr.kmp.course.presentation.component.bottomBar.NavigationBarItemModel
import com.uagr.kmp.course.presentation.component.transaction.CategoryChipItem
import com.uagr.kmp.course.presentation.theme.AppTheme
import com.uagr.kmp.course.presentation.theme.colors.backgroundBlueLight
import com.uagr.kmp.course.presentation.theme.colors.backgroundBlueSoftLight
import com.uagr.kmp.course.presentation.theme.colors.backgroundGreenSoftDark
import com.uagr.kmp.course.presentation.theme.colors.backgroundRedSoftDark
import com.uagr.kmp.course.presentation.theme.colors.textGreenDark
import com.uagr.kmp.course.presentation.theme.colors.textRedLight
import com.uagr.kmp.course.presentation.theme.colors.textWhiteDark
import com.uagr.kmp.course.presentation.ui.master.viewmodel.MasterTabs
import com.uagr.kmp.course.presentation.ui.master.viewmodel.MasterViewModel
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.operators.StatusLoading
import com.uagr.kmp.course.utils.operators.TransactionFilterHelper
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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class MovementsViewModel(
    private val getUserTokenUseCase: GetUserTokenUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase,
) : ViewModel() {

    private var _movementsUiState = MutableStateFlow(MovementsUiState())
    val movementsUiState: StateFlow<MovementsUiState> = _movementsUiState.asStateFlow()

    private var _movementsUiEvent = MutableStateFlow<MovementsUiEvent>(MovementsUiEvent.Idle)
    val movementsUiEvent: StateFlow<MovementsUiEvent> = _movementsUiEvent.asStateFlow()

    init {
        fetchHomeData()
    }

    fun fetchHomeData() = viewModelScope.launch {
        getTransactionsCategories()
        getUserToken()
    }

    fun getUserToken() = viewModelScope.launch {
        getUserTokenUseCase.invoke()
            .catch {
                //Try to Refresh token auth
                _movementsUiState.update { state -> state.copy(errorDialog = setErrorDialog(message = "Error en getUsertoken")) }
            }.collect { result ->
                getTransactionsData(token = result)
            }
    }

    fun getTransactionsData(token: String) = viewModelScope.launch {
        getTransactionsUseCase(token = token)
            .catch {
            }.collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _movementsUiState.update { state ->
                            val transactions = result.response.items

                            state.copy(
                                transactionsList = transactions,
                                filteredTransactions = TransactionFilterHelper.filter(
                                    transactions = transactions,
                                    selectedType = state.selectedType,
                                ),
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _movementsUiState.update { state -> state.copy(errorDialog = setErrorDialog(message = "Error en Dashboard")) }
                    }
                }
            }
    }

    fun onTypeSelected(type: TransactionType?) {
        _movementsUiState.update { state ->
            state.copy(
                selectedType = type,
                filteredTransactions = filterTransactions(
                    transactions = state.transactionsList,
                    selectedType = type,
                ),
            )
        }
    }

    private fun filterTransactions(
        transactions: List<TransactionModel>,
        selectedType: TransactionType?,
    ): List<TransactionModel> {
        return TransactionFilterHelper.filter(
            transactions = transactions,
            selectedType = selectedType,
        )
    }

    fun setCategories(list: List<CategoryChipItem>) = viewModelScope.launch {
        _movementsUiState.update { state ->
            state.copy(
                categories = list,
            )
        }
    }
    private suspend fun setErrorDialog(message: String? = null): ErrorDialogModel =
        ErrorDialogModel(
            title = getString(resource = Res.string.error),
            message = message ?: getString(resource = Res.string.please_try_again_later),
            primaryButtonText = getString(resource = Res.string.accept),
        )

    fun dismissErrorDialog() = viewModelScope.launch {
        _movementsUiState.update { state -> state.copy(errorDialog = null) }
    }

    fun resetUiEvent() = viewModelScope.launch {
        _movementsUiEvent.value = MovementsUiEvent.Idle
    }
}

fun MovementsViewModel.getTransactionsCategories() = viewModelScope.launch {
    val transactionCategories = listOf(
        CategoryChipItem(
            type = null,
            label = "Todos",
            backgroundColor = backgroundBlueLight,
            textColor = textWhiteDark,
        ),
        CategoryChipItem(
            type = TransactionType.INCOME,
            label = "Ingresos",
            backgroundColor = backgroundGreenSoftDark,
            textColor = textGreenDark,
        ),
        CategoryChipItem(
            type = TransactionType.EXPENSE,
            label = "Gastos",
            backgroundColor = backgroundRedSoftDark,
            textColor = textRedLight,
        ),

    )
    setCategories(transactionCategories)
}