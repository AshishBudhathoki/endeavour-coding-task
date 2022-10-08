package com.ashish.endeavour_coding_task.data.local

import androidx.room.*
import com.ashish.endeavour_coding_task.data.local.entity.FavProductEntity
import com.ashish.endeavour_coding_task.data.local.entity.ProductEntity
import com.ashish.endeavour_coding_task.data.local.entity.ProductWithFavouriteEntity

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

    @Transaction
    @Query("SELECT * FROM productentity")
    suspend fun getArtistsAndAlbums(): List<ProductWithFavouriteEntity>


    @Query(
        """
            SELECT *
            FROM productentity
            WHERE id = :id 
        """
    )
    suspend fun getProductItem(id: String): ProductWithFavouriteEntity

    @Query(
        """
            DELETE 
            FROM 
            productentity
        """
    )
    suspend fun clearProductList()

    @Query(
        """
            UPDATE productentity
            SET isFavourite = :isFavourite
            WHERE id =:id
        """
    )
    fun update(id: String, isFavourite: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteProduct(favProductEntity: FavProductEntity)

    @Query(
        """
            SELECT *
            FROM favproductentity
        """
    )
    suspend fun geFavProductList(): List<FavProductEntity>

    @Query(
        """
            DELETE 
            FROM 
            productentity
            WHERE id = :productId
        """
    )
    suspend fun removeFavProduct(productId: String)
}