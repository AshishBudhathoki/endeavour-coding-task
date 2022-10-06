package com.ashish.endeavour_coding_task.data.repository

import com.ashish.endeavour_coding_task.data.local.ProductDao
import com.ashish.endeavour_coding_task.data.mapper.toProductDomain
import com.ashish.endeavour_coding_task.data.mapper.toProductEntity
import com.ashish.endeavour_coding_task.data.remote.ProductApi
import com.ashish.endeavour_coding_task.domain.model.Product
import com.ashish.endeavour_coding_task.domain.repository.ProductRepository
import com.ashish.endeavour_coding_task.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class ProductRepositoryImpl(
    private val dao: ProductDao,
    private val productApi: ProductApi
) : ProductRepository {
    override suspend fun getProductListings(fetchFromRemote: Boolean): Flow<Resource<List<Product>>> {
        return flow {

            emit(Resource.Loading(true))
            val productListLocal = dao.getProductListFromDb()

            emit(Resource.Success(
                data = productListLocal.map {
                    it.toProductDomain()
                }
            ))

            val isDbEmpty = productListLocal.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val productListRemote = try {
                productApi.getProductListFromApi()
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }
            productListRemote?.let { apiData ->
                dao.clearProductList()
                apiData.products.map { productDto ->
                    dao.insertProduct(
                        productDto.toProductDomain().toProductEntity()
                    )
                }
                emit(Resource.Success(
                    data = dao.getProductListFromDb().map {
                        it.toProductDomain()
                    }
                ))
                emit(Resource.Loading(false))
            }
        }

    }

    override suspend fun getProductInfo(id: String): Flow<Resource<Product>> {
        return flow {
            emit(Resource.Loading(true))
            val productItem = dao.getProductItem(id)
            emit(
                Resource.Success(
                    data = productItem.toProductDomain()
                )
            )
        }
    }
}