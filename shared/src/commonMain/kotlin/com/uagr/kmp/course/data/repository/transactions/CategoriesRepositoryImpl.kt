package com.uagr.kmp.course.data.repository.transactions
/*
import com.uagr.kmp.course.data.network.datasource.transactions.CategoriesNetworkDataSource
import com.uagr.kmp.course.domain.model.transactions.CategoriesModel
import com.uagr.kmp.course.domain.repository.categories.CategoriesRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CategoriesRepositoryImpl(
    private val categoriesNetworkDataSource: CategoriesNetworkDataSource,
    private val dispatcher: CoroutineDispatcher,
): CategoriesRepository {
    override suspend fun getCategories(
        url: String,
        access_token: String
    ): Flow<NetworkResult<CategoriesModel>> = flow {
        emit(categoriesNetworkDataSource.getCategories(
            url = url,
            access_token = access_token))
    }.flowOn(dispatcher)

}*/