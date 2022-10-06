package com.ashish.endeavour_coding_task.data.local

import androidx.room.Database
import com.ashish.endeavour_coding_task.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1
)
abstract class ProductDatabase {
    abstract val dao: ProductDao
}