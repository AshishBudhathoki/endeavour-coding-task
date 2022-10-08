package com.ashish.endeavour_coding_task.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ashish.endeavour_coding_task.data.local.entity.FavProductEntity
import com.ashish.endeavour_coding_task.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class, FavProductEntity::class],
    version = 1
)
abstract class ProductDatabase : RoomDatabase() {
    abstract val dao: ProductDao
}