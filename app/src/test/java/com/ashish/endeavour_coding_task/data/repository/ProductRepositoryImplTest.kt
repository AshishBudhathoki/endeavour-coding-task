package com.ashish.endeavour_coding_task.data.repository

import app.cash.turbine.test
import com.ashish.endeavour_coding_task.data.local.ProductDao
import com.ashish.endeavour_coding_task.data.local.ProductDaoFake
import com.ashish.endeavour_coding_task.data.local.ProductDatabase
import com.ashish.endeavour_coding_task.data.local.entity.ProductEntity
import com.ashish.endeavour_coding_task.data.mapper.toProductDomain
import com.ashish.endeavour_coding_task.data.remote.ProductApi
import com.ashish.endeavour_coding_task.data.remote.dto.ProductDto
import com.ashish.endeavour_coding_task.util.Resource
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductRepositoryImplTest {

    private lateinit var repository: ProductRepositoryImpl
    private lateinit var api: ProductApi
    private lateinit var db: ProductDatabase
    private lateinit var productDao: ProductDao

    @Before
    fun setUp() {
        api = mockk(relaxed = true) {
            coEvery { getProductListFromApi() } returns mockk(relaxed = true)

        }

        productDao = ProductDaoFake()
        db = mockk(relaxed = true) {
            every { dao } returns productDao
        }

        repository = ProductRepositoryImpl(
            productApi = api,
            dao = productDao
        )
    }

    @Test
    fun `Test local database cache with fetch from remote set to true`() = runTest {
        val products = (1..25).map {
            ProductEntity(
                id = it.toString(),
                imageUrl = "imageUrl$it",
                isFavourite = false,
                name = "name$it",
                price = it.toDouble(),
                rating = it.toDouble()
            )
        }
        products.forEach {
            productDao.insertProduct(it)
        }

        repository.getProductListings(true).test {
            val startLoading = awaitItem()
            assertThat((startLoading as Resource.Loading).isLoading).isTrue()

            val productFromDb = awaitItem()
            assertThat(productFromDb is Resource.Success).isTrue()
            assertThat(productFromDb.data).isEqualTo(products.map { it.toProductDomain() })

            val productsFromApi = awaitItem()
            assertThat(productsFromApi is Resource.Success).isTrue()
            assertThat(productsFromApi.data).isEqualTo(
                productDao.getProductListFromDb().map { it.toProductDomain() }
            )

            val stopLoading = awaitItem()
            assertThat((stopLoading as Resource.Loading).isLoading).isFalse()

            awaitComplete()
        }
    }
}
