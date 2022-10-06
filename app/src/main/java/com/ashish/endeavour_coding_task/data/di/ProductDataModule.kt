package com.ashish.endeavour_coding_task.data.di

import android.app.Application
import androidx.room.Room
import com.ashish.endeavour_coding_task.data.local.ProductDatabase
import com.ashish.endeavour_coding_task.data.remote.ProductApi
import com.ashish.endeavour_coding_task.data.repository.ProductRepositoryImpl
import com.ashish.endeavour_coding_task.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideProductApi(client: OkHttpClient): ProductApi {
        return Retrofit.Builder()
            .baseUrl(ProductApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideProductDatabase(app: Application): ProductDatabase {
        return Room.databaseBuilder(
            app,
            ProductDatabase::class.java,
            "product.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        productApi: ProductApi,
        db: ProductDatabase
    ): ProductRepository {
        return ProductRepositoryImpl(
            dao = db.dao,
            productApi = productApi
        )
    }
}