package com.serdar.budges.di.repository

import com.serdar.budges.data.crypto.Data
import com.serdar.budges.service.api.RetrofitInctance

class CryptoRepository {
    suspend fun getPost(): List<Data> {
        return RetrofitInctance.api.getData().data
    }
}