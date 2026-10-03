/*
// LoginUiState.kt
// Created by Karla Judith Cervantes Higareda on 01/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.presentation.ui.login.viewmodel

import com.uagr.kmp.course.utils.operators.StatusLoading

data class LoginUiState(
    val isLoading: StatusLoading = StatusLoading.DISMISS_LOADING,
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
)
