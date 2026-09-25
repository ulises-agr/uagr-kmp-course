package com.uagr.kmp.course.domain.model.packages

data class PackageModel(
    val data : List<PackageDataModel>,
    val error : String,
    val message : String,
    val success : Boolean,
)
