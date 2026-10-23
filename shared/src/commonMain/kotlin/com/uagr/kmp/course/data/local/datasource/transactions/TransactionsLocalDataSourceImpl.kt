package com.uagr.kmp.course.data.local.datasource.transactions

import com.uagr.kmp.course.data.local.database.dao.transactions.TransactionsDao
import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.domain.mapper.transactions.toDomain
import com.uagr.kmp.course.domain.mapper.transactions.toEntity
import com.uagr.kmp.course.domain.model.transactions.TransactionsModel

class TransactionsLocalDataSourceImpl(
    private val transactionsDao: TransactionsDao,
): TransactionsLocalDataSource{
    override suspend fun insertTransactions(transaction: TransactionsModel){
        transactionsDao.insertTransaction(transaction = transaction.toEntity())
    }

    override suspend fun getTransactions(): TransactionsModel?{
        return  transactionsDao.getTransaction()?.toDomain()
    }

}
