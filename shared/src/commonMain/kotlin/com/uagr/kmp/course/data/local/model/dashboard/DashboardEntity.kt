package com.uagr.kmp.course.data.local.model.dashboard

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.uagr.kmp.course.data.local.model.transaction.TransactionsEntity
import com.uagr.kmp.course.data.network.model.response.transactions.TransactionsResponse

@Entity(tableName = "dashboard")
data class DashboardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var total_balance: String,
    var monthly_income: String,
    var monthly_expenses: String,
    var monthly_savings: String,
    var savings_rate: String,
    var budget_usage: String,
)

