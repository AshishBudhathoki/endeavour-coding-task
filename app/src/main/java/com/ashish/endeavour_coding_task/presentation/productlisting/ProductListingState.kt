package com.ashish.endeavour_coding_task.presentation.productlisting

import com.ashish.endeavour_coding_task.domain.model.Product

data class ProductListingState(
    val productList: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null,
)