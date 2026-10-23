package com.uagr.kmp.course.domain.model.dashboard


data class DashboardModel(
    var total_balance: String,
    var monthly_income: String,
    var monthly_expenses: String,
    var monthly_savings: String,
    var savings_rate: String,
    var budget_usage: String,
    var recent_transactions: List<RecentTransactionModel>,
    var top_expense_categories: List<TopExpoenseCategoriesModel>,
    var goals: List<GoalsModel>
)

data class TopExpoenseCategoriesModel(
    val category_id: String,
    val category_name: String,
    val amount: String,
    val percentage: String
)

data class GoalsModel(
    val id: String,
    val name: String,
    val target_amount: String,
    val current_amount: String,
    val target_date: String,
    val status: String,
    val created_at: String,
    val updated_at: String,
    val version: Int,
)
