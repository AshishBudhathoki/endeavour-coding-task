package com.ashish.endeavour_coding_task.presentation.productlisting

sealed class ProductListingEvent {
    object Refresh : ProductListingEvent()
}