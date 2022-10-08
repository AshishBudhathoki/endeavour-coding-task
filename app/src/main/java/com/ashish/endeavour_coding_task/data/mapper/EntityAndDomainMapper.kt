package com.ashish.endeavour_coding_task.data.mapper

import com.ashish.endeavour_coding_task.data.local.entity.FavProductEntity
import com.ashish.endeavour_coding_task.data.local.entity.ProductEntity
import com.ashish.endeavour_coding_task.data.local.entity.ProductWithFavouriteEntity
import com.ashish.endeavour_coding_task.domain.model.FavProduct
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

fun ProductWithFavouriteEntity.toProductDomain(): Product {
    return Product(
        id = productEntity.id,
        imageUrl = productEntity.imageUrl,
        isFavourite = favProductEntity?.isFavourite ?: false,
        name = productEntity.name,
        price = productEntity.price,
        rating = productEntity.rating
    )
}

fun FavProductEntity.toFavProductDomain(): FavProduct {
    return FavProduct(
        productId = productId,
        isFavourite = isFavourite
    )
}

fun FavProduct.toFavProductEntity(): FavProductEntity {
    return FavProductEntity(
        productId = productId,
        isFavourite = isFavourite
    )
}