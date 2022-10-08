package com.ashish.endeavour_coding_task.domain.usecase.favProduct

import com.ashish.endeavour_coding_task.domain.repository.ProductRepository

class AddFavouriteProductUseCase constructor(
    val repository: ProductRepository
) {
    suspend operator fun invoke(productId: String) {
        return repository.addFavProduct(productId)
    }
}
