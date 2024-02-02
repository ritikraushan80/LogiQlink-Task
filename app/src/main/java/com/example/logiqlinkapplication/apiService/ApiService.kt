package com.example.logiqlinkapplication.apiService

import com.example.logiqlinkapplication.model.InventoryData
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Ritik on 2/1/2024.
 */
interface ApiService {
    @GET("getInventory")
    suspend fun getInventory():Response<List<InventoryData>>
}