package com.ashish.endeavour_coding_task.data.remote

import com.ashish.endeavour_coding_task.data.remote.dto.ApiData
import retrofit2.http.GET

interface ProductApi {

    @GET("v3/2f06b453-8375-43cf-861a-06e95a951328")
    suspend fun getProductListFromApi(): ApiData

    companion object {
        const val BASE_URL = "https://run.mocky.io/"
    }
}