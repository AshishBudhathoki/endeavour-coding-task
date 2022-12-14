package com.ashish.endeavour_coding_task.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey
    val id: String,
    val imageUrl: String,
    val name: String,
    val price: Double,
    var isFavourite: Boolean,
    val rating: Double,
)