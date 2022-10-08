package com.ashish.endeavour_coding_task.presentation.productlisting.bottomNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(var id:Int,var title: String, var icon: ImageVector) {
    object Products : NavigationItem(0,"Products", Icons.Filled.LocalDrink)
    object Favourites : NavigationItem(1,"Favourites", Icons.Filled.Favorite)
}