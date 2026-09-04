package com.uagr.kmp.course.feature.welcome.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uagr.kmp.course.feature.welcome.presentation.WelcomeUiEffect
import com.uagr.kmp.course.feature.welcome.presentation.WelcomeUiEvent
import com.uagr.kmp.course.feature.welcome.presentation.WelcomeUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WelcomeViewModel(
    //private val paymentRepository: PaymentRepository
): ViewModel() {

    private val _state = MutableStateFlow(WelcomeUiState())
    val state: StateFlow<WelcomeUiState> = _state.asStateFlow()

    private val _uiEffect = Channel<WelcomeUiEffect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    // Entrada única de eventos desde la UI (Flujo Unidireccional)
    fun onEvent(event: WelcomeUiEvent) {
        when (event) {

            is WelcomeUiEvent.OnAmountChanged -> {
                _state.update { it.copy(totalAmount = event.newAmount) }
            }

            is WelcomeUiEvent.OnPaymentClick -> {
                processPayment()
            }

            is WelcomeUiEvent.OnDismissError -> {
                _state.update { it.copy(errorMessage = null) }
            }

            is WelcomeUiEvent.OnNavigateToHome -> {
                viewModelScope.launch {
                    _uiEffect.send(WelcomeUiEffect.NavigateToHome)
                }
            }


            else -> {}
        }
    }


    private fun processPayment() {
        viewModelScope.launch {
            // 1. Cargando
            _state.update { it.copy(isLoading = true, errorMessage = null) }

            // 2. Llamada asíncrona a la API / Repositorio
            /*
            val result = paymentRepository.processPayment(_uiState.value.totalAmount)

            // 3. Actualizar estado final
            if (result.isSuccess) {
                _state.update { it.copy(isLoading = false, isSuccess = true) }


            } else {
                _state.update { it.copy(isLoading = false, errorMessage = "Error al procesar el pago") }
            }
            */
        }
    }
/*
    private fun submitData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val result = userRepository.saveUser(_state.value.username)

            _state.update { it.copy(isLoading = false) }
            if (result.isSuccess) {
                _uiEffect.send(WelcomeUiEffect.NavigateToHome)
            } else {
                _uiEffect.send(WelcomeUiEffect.ShowToast("Error al guardar"))
            }
        }
    }
*/
}