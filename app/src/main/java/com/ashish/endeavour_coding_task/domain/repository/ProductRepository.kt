package com.ashish.endeavour_coding_task.domain.repository

import com.ashish.endeavour_coding_task.domain.model.Product
import com.ashish.endeavour_coding_task.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProductListings(
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Product>>>

    suspend fun getProductInfo(
        id: String
    ): Flow<Resource<Product>>
}