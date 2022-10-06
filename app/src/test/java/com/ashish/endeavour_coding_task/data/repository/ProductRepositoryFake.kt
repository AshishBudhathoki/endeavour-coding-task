package com.ashish.endeavour_coding_task.data.repository

import com.ashish.endeavour_coding_task.data.local.entity.ProductEntity
import com.ashish.endeavour_coding_task.domain.model.Product
import com.ashish.endeavour_coding_task.domain.repository.ProductRepository
import com.ashish.endeavour_coding_task.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepositoryFake : ProductRepository {
    val products = (1..25).map {
        Product(
            id = it.toString(),
            imageUrl = "imageUrl$it",
            isFavourite = false,
            name = "name$it",
            price = it.toDouble(),
            rating = it.toDouble()
        )
    }

    override suspend fun getProductListings(fetchFromRemote: Boolean): Flow<Resource<List<Product>>> {
        return flow {
            emit(Resource.Success(products))
        }
    }

    override suspend fun getProductInfo(id: String): Flow<Resource<Product>> {
        TODO("Not yet implemented")
    }
}