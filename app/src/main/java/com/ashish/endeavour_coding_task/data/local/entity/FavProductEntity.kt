package com.ashish.endeavour_coding_task.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class FavProductEntity(
    @PrimaryKey()
    val productId: String,
    val isFavourite: Boolean = true
)