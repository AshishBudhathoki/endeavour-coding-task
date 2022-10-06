package com.ashish.endeavour_coding_task.data.remote.dto

data class ProductDto(
    val id: String,
    val imageURL: String,
    val isAddToCartEnable: Boolean,
    val ratingCount: Double,
    val saleUnitPrice: Double,
    val title: String,
)