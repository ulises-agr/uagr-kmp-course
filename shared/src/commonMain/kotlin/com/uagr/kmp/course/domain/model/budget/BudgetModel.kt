package com.uagr.kmp.course.domain.model.budget

data class BudgetModel(
    val success: Boolean = false,
    val message: String = "",
    val data: BudgetDataModel? = null,
)
