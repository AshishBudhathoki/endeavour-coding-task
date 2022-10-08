package com.ashish.endeavour_coding_task.domain.usecase.product

import com.ashish.endeavour_coding_task.domain.repository.ProductRepository
import com.ashish.endeavour_coding_task.util.Resource
import kotlinx.coroutines.flow.Flow

class UpdateProductFavoriteUseCase constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: String, isFavourite: Boolean) {
        return repository.updateProductFavourite(id, isFavourite)
    }

}