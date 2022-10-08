package com.ashish.endeavour_coding_task.domain.usecase

import com.ashish.endeavour_coding_task.domain.usecase.favProduct.AddFavouriteProductUseCase
import com.ashish.endeavour_coding_task.domain.usecase.favProduct.GetFavProductsUseCase
import com.ashish.endeavour_coding_task.domain.usecase.favProduct.RemoveFavProductUseCase
import com.ashish.endeavour_coding_task.domain.usecase.product.GetProductListUseCase
import com.ashish.endeavour_coding_task.domain.usecase.product.UpdateProductFavoriteUseCase

data class ProductUseCases(
    val getProductListUseCase: GetProductListUseCase,
    val updateProductFavoriteUseCase: UpdateProductFavoriteUseCase,
    val addFavouriteProductUseCase: AddFavouriteProductUseCase,
    val removeFavProductUseCase: RemoveFavProductUseCase,
    val getFavProductsUseCase: GetFavProductsUseCase
)