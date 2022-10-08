package com.ashish.endeavour_coding_task.presentation.productlisting

import com.ashish.endeavour_coding_task.MainCoroutineRule
import com.ashish.endeavour_coding_task.data.repository.ProductRepositoryFake
import com.ashish.endeavour_coding_task.domain.usecase.product.GetProductListUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductListingViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ProductListingViewModel
    private lateinit var getProductListUseCase: GetProductListUseCase
    private lateinit var repositoryFake: ProductRepositoryFake

    @Before
    fun setUp() {
        repositoryFake = ProductRepositoryFake()
        getProductListUseCase = GetProductListUseCase(repositoryFake)
        viewModel = ProductListingViewModel(getProductListUseCase)

        coroutineRule.dispatcher.scheduler.advanceUntilIdle()
    }

    @Test
    fun `when viewmodel is created list is populated`() = runTest {

        assertThat(
            viewModel.state.productList
        ).isEqualTo(repositoryFake.products)

    }
}