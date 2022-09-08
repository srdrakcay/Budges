package com.serdar.budges.service.transaction

import androidx.lifecycle.LiveData
import androidx.room.*
import com.serdar.budges.data.transaction.Transaction

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

    @Query("SELECT * FROM transactions WHERE expanse")
    fun getExpanseList():  LiveData<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE income")
    fun getIncomeList():  LiveData<List<Transaction>>


}
