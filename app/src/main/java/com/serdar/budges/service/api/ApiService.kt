package com.serdar.budges.service.api

import com.serdar.budges.data.crypto.CryptoModel
import com.serdar.budges.data.crypto.Data
import com.serdar.budges.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v2/assets")

    suspend fun getData(): ApiResponse
}