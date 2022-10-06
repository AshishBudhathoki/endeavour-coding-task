package com.ashish.endeavour_coding_task.data.mapper

import com.ashish.endeavour_coding_task.data.local.entity.ProductEntity
import com.ashish.endeavour_coding_task.domain.model.Product

fun Product.toEntityMapper(): ProductEntity {
    return ProductEntity(
        id = id,
        imageUrl = imageUrl,
        isFavourite = isFavourite,
        name = name,
        price = price,
        rating = rating
    )
}

fun ProductEntity.toDomainMapper(): Product {
    return Product(
        id = id,
        imageUrl = imageUrl,
        isFavourite = isFavourite,
        name = name,
        price = price,
        rating = rating
    )
}