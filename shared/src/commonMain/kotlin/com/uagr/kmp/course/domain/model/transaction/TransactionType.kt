/*
 * TransactionType.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.model.transaction

enum class TransactionType {
    INCOME,
    EXPENSE,
    UNKNOWN;

    companion object {
        fun from(type: String?): TransactionType =
            when (type?.uppercase()) {
                "INCOME" -> INCOME
                "EXPENSE" -> EXPENSE
                else -> UNKNOWN
            }
    }
}
