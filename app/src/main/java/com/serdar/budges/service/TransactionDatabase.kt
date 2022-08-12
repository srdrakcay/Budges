package com.serdar.budges.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.serdar.budges.data.Transaction

@Database(entities = [Transaction::class], version =2, exportSchema = false)

    abstract class TransactionDatabase: RoomDatabase() {

        abstract fun transactionDao():TransactionDao

        companion object{
            @Volatile
            private var INSTANCE:TransactionDatabase? = null

            fun getDatabase(context: Context):TransactionDatabase{
                val tempInstance= INSTANCE
                if (tempInstance != null){
                    return tempInstance
                }
                synchronized(this){
                    val instance = Room.databaseBuilder(context,TransactionDatabase::class.java,"transaction_database").fallbackToDestructiveMigration().build()
                    INSTANCE=instance
                    return instance
                }
            }

        }
    }
