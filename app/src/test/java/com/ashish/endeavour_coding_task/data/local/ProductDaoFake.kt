package com.ashish.endeavour_coding_task.data.local

import com.ashish.endeavour_coding_task.data.local.entity.ProductEntity

class ProductDaoFake : ProductDao {

    private var products = emptyList<ProductEntity>()

    override suspend fun insertProduct(product: ProductEntity) {
        products = products + product
    }

    override suspend fun getProductListFromDb(): List<ProductEntity> {
        return products
    }

    override suspend fun getProductItem(id: String): ProductEntity {
        return products.find {
            it.id == id
        }!!
    }

    override suspend fun clearProductList() {
        products = emptyList()
    }
}