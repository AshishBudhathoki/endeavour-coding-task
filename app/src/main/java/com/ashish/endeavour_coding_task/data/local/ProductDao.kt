package com.ashish.endeavour_coding_task.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ashish.endeavour_coding_task.data.local.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Query(
        """
            SELECT *
            FROM productentity
        """
    )
    suspend fun getProductListFromDb(): List<ProductEntity>


    @Query(
        """
            SELECT *
            FROM productentity
            WHERE id = :id 
        """
    )
    suspend fun getProductItem(id: String): ProductEntity

    @Query(
        """
            DELETE 
            FROM 
            productentity
        """
    )
    suspend fun clearProductList()
}