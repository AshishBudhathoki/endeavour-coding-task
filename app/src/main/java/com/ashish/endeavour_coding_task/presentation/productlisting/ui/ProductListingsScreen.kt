package com.ashish.endeavour_coding_task.presentation.productlisting.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.ashish.endeavour_coding_task.presentation.productlisting.ProductListingEvent
import com.ashish.endeavour_coding_task.presentation.productlisting.ProductListingState
import com.ashish.endeavour_coding_task.presentation.productlisting.ProductListingViewModel
import com.ashish.endeavour_coding_task.ui.theme.Purple700
import com.ashish.endeavour_coding_task.ui.theme.Teal200
import com.ashish.endeavour_coding_task.util.ui.Dimensions
import com.ashish.endeavour_coding_task.util.ui.LocalSpacing
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ProductListingsScreen(
    viewModel: ProductListingViewModel = hiltViewModel(),
    onNavigateToSearch: (String) -> Unit,
) {
    val spacing = LocalSpacing.current

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )
    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            TopAppBarLayout()

            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = {
                    viewModel.onEvent(ProductListingEvent.Refresh)
                }
            ) {
                if (state.error != null && state.productList.isEmpty()) {
                    ErrorText(state.error, spacing)
                }
                ProductListLazyColumn(state, spacing, onNavigateToSearch)

            }
        }
    }
}

@Composable
private fun ErrorText(error: String, spacing: Dimensions) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacing.spaceSmall),
        text = error,
        color = MaterialTheme.colors.error,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun ProductListLazyColumn(
    state: ProductListingState,
    spacing: Dimensions,
    onNavigateToSearch: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("test_lazy_column")

    ) {
        items(state.productList.size) { i ->
            val product = state.productList[i]
            ProductListItem(
                product = product,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.spaceMedium),
                {
                    onNavigateToSearch(it)
                }
            ) {

            }
            if (i < state.productList.size) {
                Divider(
                    modifier = Modifier.padding(
                        horizontal = spacing.spaceMedium
                    )
                )
            }
        }
    }
}

@Composable
private fun TopAppBarLayout() {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Products",
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                color = White
            )
        },
        backgroundColor = Purple700
    )
}