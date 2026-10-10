/*
 * Operations.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.utils.operators

import androidx.room3.Transaction
import com.uagr.kmp.course.domain.model.transaction.TransactionModel
import com.uagr.kmp.course.domain.model.transaction.TransactionType

enum class StatusLoading {
    SHOW_LOADING,
    DISMISS_LOADING,
}

object TransactionFilterHelper {

    fun filter(
        transactions: List<TransactionModel>,
        selectedType: TransactionType?,
    ): List<TransactionModel> {
        return transactions.filter { transaction ->
            selectedType == null || transaction.type == selectedType
        }
    }
}
