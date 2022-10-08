package com.ashish.endeavour_coding_task.domain.usecase.product

import com.ashish.endeavour_coding_task.domain.model.Product
import com.ashish.endeavour_coding_task.domain.repository.ProductRepository
import com.ashish.endeavour_coding_task.util.Resource
import kotlinx.coroutines.flow.Flow

class GetProductListUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(fetchFromRemote: Boolean): Flow<Resource<List<Product>>> {
        return repository.getProductListings(fetchFromRemote)
    }
}