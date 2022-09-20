package com.serdar.budges.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.serdar.budges.data.transaction.Transaction
import com.serdar.budges.di.repository.TransactionRepository
import com.serdar.budges.service.transaction.TransactionDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionViewModel(application: Application) : AndroidViewModel(application) {


    val readAllData: LiveData<List<Transaction>>
    val readExpenseData: LiveData<List<Transaction>>
    val readIncomeData: LiveData<List<Transaction>>


    private val repository: TransactionRepository


    init {
        val transactionDao = TransactionDatabase.getDatabase(application).transactionDao()
        repository = TransactionRepository(transactionDao)
        readAllData = transactionDao.readAllData()
        readExpenseData = transactionDao.getExpenseList()
        readIncomeData = transactionDao.getIncomeList()


    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTransaction(transaction)

        }
    }

    fun updateTransaction(transaction: Transaction) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTransaction(transaction)
        }
    }

    fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTransaction(transaction)
        }
    }


}