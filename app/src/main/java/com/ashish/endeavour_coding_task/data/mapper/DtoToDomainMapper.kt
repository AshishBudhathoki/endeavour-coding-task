package com.ashish.endeavour_coding_task.data.mapper

import com.ashish.endeavour_coding_task.data.remote.dto.ProductDto
import com.ashish.endeavour_coding_task.domain.model.Product

fun ProductDto.toProductDomain(): Product {
    return Product(
        id = id,
        imageUrl = imageURL,
        isFavourite = false,
        name = title,
        price = saleUnitPrice,
        rating = ratingCount
    )
}