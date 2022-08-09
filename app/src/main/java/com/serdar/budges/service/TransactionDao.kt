package com.serdar.budges.service

import androidx.lifecycle.LiveData
import androidx.room.*
import com.serdar.budges.di.data.Transaction

@Dao
interface TransactionDao {

    @Query(value = "SELECT * FROM transactions ORDER BY id ASC")
    fun readAllData(): LiveData<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTransaction(transaction: Transaction):Long


    @Update
    fun updateTransaction(transaction: Transaction)

    @Delete
    fun deleteTransaction(transaction: Transaction)

    @Query("DELETE FROM transactions ")
    fun deleteAllTransaction()
}
