package com.serdar.budges.data.transaction

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "transactions")
data class Transaction(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "transaction") val transaction: String,
    @ColumnInfo(name = "amaount") val amount: Double = 0.0,
    @ColumnInfo(name = "desciption") val description: String,
    @ColumnInfo(name = "incomeExpanseType") val incomeExpanseType: String
) : Parcelable

