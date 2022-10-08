package com.ashish.endeavour_coding_task.domain.di

import com.ashish.endeavour_coding_task.domain.repository.ProductRepository
import com.ashish.endeavour_coding_task.domain.usecase.product.GetProductListUseCase
import com.ashish.endeavour_coding_task.domain.usecase.ProductUseCases
import com.ashish.endeavour_coding_task.domain.usecase.favProduct.AddFavouriteProductUseCase
import com.ashish.endeavour_coding_task.domain.usecase.favProduct.GetFavProductsUseCase
import com.ashish.endeavour_coding_task.domain.usecase.favProduct.RemoveFavProductUseCase
import com.ashish.endeavour_coding_task.domain.usecase.product.UpdateProductFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/***
 * Dependencies for usecases from domain
 */
@Module
@InstallIn(ViewModelComponent::class)
object ProductDomainModule {

    @ViewModelScoped
    @Provides
    fun provideProductUseCases(
        repository: ProductRepository
    ): ProductUseCases {
        return ProductUseCases(
            GetProductListUseCase(repository),
            UpdateProductFavoriteUseCase(repository),
            AddFavouriteProductUseCase(repository),
            RemoveFavProductUseCase(repository),
            GetFavProductsUseCase(repository)
        )
    }
}