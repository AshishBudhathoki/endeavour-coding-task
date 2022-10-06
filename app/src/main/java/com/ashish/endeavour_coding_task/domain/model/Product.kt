package com.ashish.endeavour_coding_task.domain.model

data class Product(
    val id: String,
    val imageUrl: String,
    val name: String,
    val price: Double,
    val priceMessage: String,
    val isFavourite: Boolean,
    val rating: Double,
)