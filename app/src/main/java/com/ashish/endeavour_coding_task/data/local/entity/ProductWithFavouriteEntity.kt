package com.ashish.endeavour_coding_task.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ProductWithFavouriteEntity(
    @Embedded val productEntity: ProductEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId"
    )
    val favProductEntity: FavProductEntity?
)