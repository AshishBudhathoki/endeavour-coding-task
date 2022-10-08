package com.ashish.endeavour_coding_task.domain.repository

import com.ashish.endeavour_coding_task.domain.model.FavProduct
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

    suspend fun updateProductFavourite(
        id: String,
        isFavourite: Boolean
    )

    suspend fun addFavProduct(
        id: String
    )

    suspend fun removeFavProduct(
        id: String
    )

    suspend fun getFavProducts(): Flow<List<FavProduct>>
}