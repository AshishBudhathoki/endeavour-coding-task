package com.ashish.endeavour_coding_task.domain.usecase.favProduct

import com.ashish.endeavour_coding_task.domain.model.FavProduct
import com.ashish.endeavour_coding_task.domain.repository.ProductRepository
import com.ashish.endeavour_coding_task.util.Resource
import kotlinx.coroutines.flow.Flow

class GetFavProductsUseCase constructor(
    private val repository: ProductRepository
) {
    suspend fun invoke(): Flow<List<FavProduct>> {
        return repository.getFavProducts()
    }
}