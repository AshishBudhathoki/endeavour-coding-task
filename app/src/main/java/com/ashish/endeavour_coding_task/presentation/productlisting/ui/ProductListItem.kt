package com.ashish.endeavour_coding_task.presentation.productlisting.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ashish.endeavour_coding_task.domain.model.Product
import com.ashish.endeavour_coding_task.util.ui.LocalSpacing
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle

@Composable
fun ProductListItem(
    product: Product,
    modifier: Modifier = Modifier,
    onClickedProduct: (String) -> Unit,
    onToggleFav: (String, Boolean) -> Unit
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    Row(
        modifier = modifier.clickable { onClickedProduct(product.id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                ImageLoader(imageUrl = product.imageUrl, context)
                Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                Column() {
                    Text(
                        text = product.name,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onBackground,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))

                    RatingBarView(rating = product.rating)

                    Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                    Text(
                        text = "Price: ${product.price}",
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground
                    )

                    Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))

                    IconButton(onClick = {
                        Log.d("Add Cart clicked: %s", product.name)
                    }) {
                        Row {
                            Icon(
                                imageVector = Icons.Filled.ShoppingCart,
                                contentDescription = "Shopping Cart",
                                tint = Color.Black
                            )
                            Text("Add to cart")
                        }
                    }
                }

                FavoriteButton(isFavourite = product.isFavourite) {
                    Log.d("Favourite is: %s", "${product.name}  $it")
                    onToggleFav(product.id, it)
                }
            }

            Spacer(modifier = Modifier.height(spacing.spaceSmall))

        }
    }
}

@Composable
fun ImageLoader(imageUrl: String, context: Context) {
    Image(
        painter = rememberAsyncImagePainter(remember(imageUrl) {
            ImageRequest.Builder(context)
                .data(imageUrl)
                .diskCacheKey(imageUrl)
                .memoryCacheKey(imageUrl)
                .build()
        }
        ),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(LocalSpacing.current.imageDefaultSize)
    )
}

@Composable
fun RatingBarView(rating: Double) {
    RatingBar(
        value = rating.toFloat(),
        config = RatingBarConfig()
            .style(RatingBarStyle.HighLighted)
            .size(20.dp),
        onRatingChanged = {},
        onValueChange = {}
    )

}

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color(0xffE91E63),
    isFavourite: Boolean,
    onToggleFav: (Boolean) -> Unit
) {

    Log.d("ISFAVIS %S", isFavourite.toString())

    var isFavorite by remember { mutableStateOf(isFavourite) }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
            onToggleFav(isFavorite)
        }
    ) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null,
        )
    }

}