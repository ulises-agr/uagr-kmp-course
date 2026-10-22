package com.uagr.kmp.course.domain.mapper.home

import com.uagr.kmp.course.data.network.model.response.home.DashboardResponse
import com.uagr.kmp.course.data.network.model.response.home.TransactionResponse
import com.uagr.kmp.course.domain.model.home.DashboardModel
import com.uagr.kmp.course.domain.model.home.TransactionModel

fun DashboardResponse.toDomain(): DashboardModel {
    return DashboardModel(
        totalBalance = this.total_balance?.let { "$$it" } ?: "$0.00",
        monthlyIncome = this.monthly_income?.let { "+$$it" } ?: "+$0.00",
        monthlyExpenses = this.monthly_expenses?.let { "-$$it" } ?: "-$0.00",
        monthlySavings = this.monthly_savings?.let { "$$it" } ?: "$0.00",
        recentTransactions = this.recent_transactions?.map { it.toDomain() }.orEmpty(),
    )
}

fun TransactionResponse.toDomain(): TransactionModel {
    val isExpenseType = this.type.equals("EXPENSE", ignoreCase = true)
    val prefix = if (isExpenseType) "-" else "+"
    val amountStr = this.amount ?: "0.00"

    return TransactionModel(
        id = this.id.orEmpty(),
        description = this.description.orEmpty().ifEmpty { "Movimiento" },
        categoryOrSubtitle = this.transaction_date?.take(10) ?: "Reciente",
        amountFormatted = "$prefix$$amountStr",
        isExpense = isExpenseType,
    )
}