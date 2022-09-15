package com.serdar.budges.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.serdar.budges.data.transaction.Transaction
import com.serdar.budges.di.repository.TransactionRepository
import com.serdar.budges.service.transaction.TransactionDatabase

class IncomeDashViewModel(application: Application) : AndroidViewModel(application) {

    val readIncome: LiveData<List<Transaction>>

    private val repository: TransactionRepository

    init {
        val transactionDao = TransactionDatabase.getDatabase(application).transactionDao()
        repository = TransactionRepository(transactionDao)
        readIncome = transactionDao.getIncomeList()
    }
}