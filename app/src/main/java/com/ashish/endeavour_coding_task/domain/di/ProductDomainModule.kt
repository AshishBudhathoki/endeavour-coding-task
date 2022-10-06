package com.ashish.endeavour_coding_task.domain.di

import com.ashish.endeavour_coding_task.domain.repository.ProductRepository
import com.ashish.endeavour_coding_task.domain.usecase.GetProductListUseCase
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
    fun provideGetProductListUseCase(
        repository: ProductRepository
    ): GetProductListUseCase {
        return GetProductListUseCase(repository)
    }
}