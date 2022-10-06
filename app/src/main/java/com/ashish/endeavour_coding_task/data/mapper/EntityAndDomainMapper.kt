package com.ashish.endeavour_coding_task.data.mapper

import com.ashish.endeavour_coding_task.data.local.entity.ProductEntity
import com.ashish.endeavour_coding_task.domain.model.Product

fun Product.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        imageUrl = imageUrl,
        isFavourite = isFavourite,
        name = name,
        price = price,
        rating = rating
    )
}

fun ProductEntity.toProductDomain(): Product {
    return Product(
        id = id,
        imageUrl = imageUrl,
        isFavourite = isFavourite,
        name = name,
        price = price,
        rating = rating
    )
}