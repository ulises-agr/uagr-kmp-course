package com.uagr.kmp.course.domain.model.budget


data class BudgetListResultModel(
    val data : List<BudgetListModel>
)
data class BudgetListModel(
    val id: String,
    val category_id: String,
    val amount: String,
    val period: String,
    val start_date: String,
    val end_date: String,
    val created_at: String,
    val updated_at: String,
    val version: Int,
    )

data class BudgetWithCategorytModel(
    val id: String,
    val category_name: String,
    val amount: String,
    val period: String,
    val start_date: String,
    val end_date: String,
)