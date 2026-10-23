package com.uagr.kmp.course.data.network.model.response.dashboard

import kotlinx.serialization.Serializable

@Serializable
data class GoalsResponse(
    val id: String? = null,
    val name: String? = null,
    val target_amount: String? = null,
    val current_amount: String? = null,
    val target_date: String? = null,
    val status: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val version: Int? = null,

    )