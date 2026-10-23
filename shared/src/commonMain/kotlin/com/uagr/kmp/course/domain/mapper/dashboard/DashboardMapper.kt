package com.uagr.kmp.course.domain.mapper.dashboard

import com.uagr.kmp.course.data.network.model.response.dashboard.DashboardResponse
import com.uagr.kmp.course.data.network.model.response.dashboard.GoalsResponse
import com.uagr.kmp.course.data.network.model.response.dashboard.RecentTransactionResponse
import com.uagr.kmp.course.data.network.model.response.dashboard.TopExpenseCategoryResponse
import com.uagr.kmp.course.domain.mapper.transactions.toTransactionType
import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
import com.uagr.kmp.course.domain.model.dashboard.GoalsModel
import com.uagr.kmp.course.domain.model.dashboard.RecentTransactionModel
import com.uagr.kmp.course.domain.model.dashboard.TopExpoenseCategoriesModel
import com.uagr.kmp.course.domain.model.transactions.TransactionType
import kotlin.String

fun DashboardResponse.toDomain(): DashboardModel =
    DashboardModel(
        total_balance = total_balance?.normalizeAmount().orEmpty(),
        monthly_income = monthly_income?.normalizeAmount().orEmpty(),
        monthly_expenses = monthly_expenses?.normalizeAmount().orEmpty(),
        monthly_savings = monthly_savings?.normalizeAmount().orEmpty(),
        savings_rate = savings_rate?.normalizeAmount().orEmpty(),
        budget_usage = budget_usage?.normalizeAmount().orEmpty(),
        recent_transactions = recent_transactions.orEmpty().map { it.toDomain() },
        top_expense_categories = top_expense_categories.orEmpty().map {it.toDomain()} ,
        goals = goals.orEmpty().map { it.toDomain() },
    )
fun RecentTransactionResponse.toDomain(): RecentTransactionModel =
    RecentTransactionModel(
        id = id.orEmpty(),
        account_id = account_id.orEmpty(),
        category_id = category_id.orEmpty(),
        type = type?.toTransactionType() ?: TransactionType.NONE,
        amount = amount.orEmpty(),
        currency = currency.orEmpty(),
        description = description.orEmpty(),
        notes = notes.orEmpty(),
        transaction_date = transaction_date.orEmpty(),
        created_at = created_at.orEmpty(),
        updated_at = updated_at.orEmpty(),
        version = version ?: 0
    )
fun TopExpenseCategoryResponse.toDomain(): TopExpoenseCategoriesModel =
    TopExpoenseCategoriesModel(
        category_id = category_id.orEmpty(),
        category_name = category_name.orEmpty(),
        amount = amount.orEmpty(),
        percentage = percentage.orEmpty(),

    )
fun GoalsResponse.toDomain(): GoalsModel =
    GoalsModel(
        id = id.orEmpty(),
        name = name.orEmpty(),
        target_amount = target_amount.orEmpty(),
        current_amount = current_amount.orEmpty(),
        target_date = target_date.orEmpty(),
        status = status.orEmpty(),
        created_at = created_at.orEmpty(),
        updated_at = updated_at.orEmpty(),
        version = version ?: 0,
        )
fun String.normalizeAmount(): String {
    val sign = when (firstOrNull()) {
        '-' -> "-"
        '+' -> "+"
        else -> ""
    }

    val number = removePrefix("-")
        .removePrefix("+")
        .trimStart('0')
        .ifEmpty { "0" }

    return sign + "$" + if (number.startsWith(".")) "0$number" else number
}