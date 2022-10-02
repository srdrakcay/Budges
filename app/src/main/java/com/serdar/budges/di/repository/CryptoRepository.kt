package com.serdar.budges.di.repository

import com.serdar.budges.data.crypto.Data
import com.serdar.budges.service.api.RetrofitInstance

class CryptoRepository {
    suspend fun getPost(): List<Data> {
        return try {
            RetrofitInstance.api.getData().data
        } catch (exception: Exception) {
            listOf()
        }
    }
}
