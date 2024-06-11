package com.koreatech.simplecompoestudy.view

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.koreatech.simplecompoestudy.component.ImageItem
import com.koreatech.simplecompoestudy.component.SearchField
import com.koreatech.simplecompoestudy.view.viewmodel.MainViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun MainView(
    modifier: Modifier = Modifier,
    context: Context,
    viewModel: MainViewModel,
) {
    val state by viewModel.collectAsState()
    val images = viewModel.images.collectAsLazyPagingItems()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchField(
            searchText = state.searchText,
            onSearchTextChange = viewModel::updateSearchText,
            onClear = viewModel::clearSearchText
        )

        if (images.loadState.hasError) {
            Text(text = "에러 발생!")
        }

        LazyColumn(
            modifier = Modifier
        ) {
            items(images.itemCount) { index ->
                ImageItem(
                    context = context,
                    imageDocument = images[index]
                )
            }
        }
    }
}