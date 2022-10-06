package com.ashish.endeavour_coding_task.presentation.productlisting

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashish.endeavour_coding_task.domain.usecase.GetProductListUseCase
import com.ashish.endeavour_coding_task.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListingViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase
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
        }
    }

    private fun getProductList(fetchFromRemote: Boolean = false) {
        viewModelScope.launch {
            getProductListUseCase.invoke(fetchFromRemote).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { productList ->
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