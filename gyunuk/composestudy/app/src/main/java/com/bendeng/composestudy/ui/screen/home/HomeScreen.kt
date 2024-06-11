package com.bendeng.composestudy.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bendeng.composestudy.R
import com.bendeng.composestudy.domain.model.DocumentData
import com.bendeng.composestudy.ui.components.LoadingDialog
import com.bendeng.composestudy.ui.components.RoundedGlideImage
import com.bendeng.composestudy.ui.util.DateTimeUtils

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.container.stateFlow.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is HomeSideEffect.Toast -> Toast.makeText(
                    context,
                    sideEffect.text,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    Surface(
        modifier = modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ) {
        Column(modifier = modifier) {
            HomeSearchBar(
                text = state.searchText,
                onSearch = viewModel::searchPhotoList,
                onUpdateSearchText = viewModel::updateSearchText,
                onClearSearchText = viewModel::clearSearchText
            )
            ArticleList(images = state.photos)
        }
        LoadingDialog(show = state.loading)
    }
}

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    images: List<DocumentData>,
) {
    LazyColumn(modifier = modifier.padding(vertical = 20.dp).fillMaxWidth()) {
        items(items = images) { item ->
            ArticleImage(item, modifier = Modifier.padding(top = 20.dp))
        }
    }
}

@Composable
fun ArticleImage(
    image: DocumentData,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth().heightIn(120.dp),
    ) {
        Row(
            verticalAlignment = Alignment.Top,
        ) {
            Box(modifier = Modifier.wrapContentSize()) {
                RoundedGlideImage(
                    imageModel = image.imageUrl,
                    contentDescription = null,
                    size = 120.dp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.fillMaxHeight().weight(1f),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(
                    modifier = Modifier.heightIn(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_image),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = image.displaySitename,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Text(
                    text = image.docUrl,
                    fontSize = 10.sp,
                    color = Color.Gray,
                )

                Text(
                    text = DateTimeUtils.formatDateTime(image.datetime),
                    fontSize = 10.sp,
                    color = Color.Gray
                )
            }
            Text(
                text = image.collection,
                color = Color.Blue,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSearchBar(
    modifier: Modifier = Modifier,
    text: String,
    onSearch: () -> Unit,
    onUpdateSearchText: (String) -> Unit,
    onClearSearchText: () -> Unit,
) {

    val focusManager = LocalFocusManager.current

    TextField(
        value = text,
        onValueChange = { onUpdateSearchText(it) },
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
            onSearch()
        }),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        maxLines = 1,
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        trailingIcon = {
            if (text.isNotEmpty()) {
                IconButton(onClick = { onClearSearchText() }) {
                    Icon(Icons.Default.Close, contentDescription = null)
                }
            }
        },
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        modifier = modifier.fillMaxWidth().heightIn(60.dp)
    )
}