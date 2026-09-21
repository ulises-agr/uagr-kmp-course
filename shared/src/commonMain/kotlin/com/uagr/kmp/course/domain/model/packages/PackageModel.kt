package com.uagr.kmp.course.domain.model.packages

data class PackageModel(
    val title: String,
    val packageDescription: String,
    val usedAmount: String,
    val unit: String,
    val renewalText: String,
    val buttonText: String,
    val hasInfoIcon: Boolean,
)