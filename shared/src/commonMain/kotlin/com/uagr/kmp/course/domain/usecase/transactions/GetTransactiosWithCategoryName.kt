package com.uagr.kmp.course.domain.usecase.transactions

import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
//import com.uagr.kmp.course.domain.repository.categories.CategoriesRepository

class GetRecentTransactiosWithCategoryName(
    private val transactionsRepository: GetTransactionsUseCase,
    //private val categoriesRepository: CategoriesRepository
){
    suspend operator fun invoke(
        url: String,
        acces_token: String
    ):List<DashboardModel>{
        var transaction = transactionsRepository.getTranslactions()
        return emptyList()
    }
}