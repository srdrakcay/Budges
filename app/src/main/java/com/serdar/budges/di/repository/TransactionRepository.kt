package com.serdar.budges.di.repository

import androidx.lifecycle.LiveData
import com.serdar.budges.di.data.Transaction
import com.serdar.budges.service.TransactionDao

class TransactionRepository (private val transactionDao: TransactionDao) {

    val readAllDataTransaction : LiveData<List<Transaction>> = transactionDao.readAllData()


    suspend fun addTransaction(transaction: Transaction){
        transactionDao.addTransaction(transaction)
    }
    suspend fun updateTransaction(transaction: Transaction){
        transactionDao.updateTransaction(transaction)
    }
    suspend fun deleteTransaction(transaction: Transaction){
        transactionDao.deleteTransaction(transaction)
    }
}