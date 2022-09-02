package com.serdar.budges.service.api

import com.serdar.budges.model.ApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("v2/assets")

    suspend fun getData(): ApiResponse
}