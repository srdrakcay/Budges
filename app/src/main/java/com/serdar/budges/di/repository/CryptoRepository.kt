package com.serdar.budges.di.repository

import com.serdar.budges.data.crypto.CryptoModel
import com.serdar.budges.data.crypto.Data
import com.serdar.budges.service.api.RetrofitInctance
import retrofit2.Response

class CryptoRepository {
    suspend fun  getPost(): List<Data>{
        return RetrofitInctance.api.getData().data
    }
}