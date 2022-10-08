package com.ashish.endeavour_coding_task.presentation.productlisting

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashish.endeavour_coding_task.domain.usecase.ProductUseCases
import com.ashish.endeavour_coding_task.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductListingViewModel @Inject constructor(
    private val productUseCases: ProductUseCases
) : ViewModel() {
    var state by mutableStateOf(ProductListingState())

    init {
        getProductList()
    }


    fun onEvent(event: ProductListingEvent) {
        when (event) {
            is ProductListingEvent.Refresh -> {
                getProductList(true)
            }

            is ProductListingEvent.Product -> {
                getProductList(false)
            }

            is ProductListingEvent.OnFavouriteClicked -> {
                updateIsFavourite(event.productId, event.isFavourite)
                getProductList(false)
            }

            is ProductListingEvent.Favourite -> {
                state = state.copy(
                    productList = state.productList.filter {
                        it.isFavourite
                    }
                )
            }
        }
    }

    private fun updateIsFavourite(productId: String, isFavourite: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (isFavourite) {
                    productUseCases.addFavouriteProductUseCase.invoke(productId)
                } else {
                    productUseCases.removeFavProductUseCase.invoke(productId)
                }
            }
        }
    }


    private fun getProductList(fetchFromRemote: Boolean = false) {
        viewModelScope.launch {
            productUseCases.getProductListUseCase.invoke(fetchFromRemote).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { productList ->
                            Log.d("ProductList %s", productList.toString())
                            state = state.copy(
                                productList = productList
                            )
                        }
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                }
            }
        }
    }
}