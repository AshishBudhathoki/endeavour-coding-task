package com.ashish.endeavour_coding_task.domain.usecase.favProduct

import com.ashish.endeavour_coding_task.domain.repository.ProductRepository

class RemoveFavProductUseCase constructor(
    private val repository: ProductRepository
) {
    suspend fun invoke(productId: String) {
        return repository.removeFavProduct(productId)
    }
}