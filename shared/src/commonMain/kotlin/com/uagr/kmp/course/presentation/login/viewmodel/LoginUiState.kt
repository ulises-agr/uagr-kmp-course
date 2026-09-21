package com.uagr.kmp.course.presentation.login.viewmodel

import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import com.uagr.kmp.course.utils.operators.StatusLoading

data class LoginUiState (
    val isLoading : StatusLoading = StatusLoading.HIDE_LOADING,
    val email : String = "",
    val emailError : String = "",
    val password : String = "",
    val passwordError : String = "",
    val passwordVisible : Boolean = false,
    val errorDialog : ErrorDialogModel? = null
)