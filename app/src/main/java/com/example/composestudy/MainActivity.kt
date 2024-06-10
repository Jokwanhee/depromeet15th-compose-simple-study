package com.example.composestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchDocument()
        }
    }

    @Composable
    fun SearchDocument(viewModel: SearchViewModel = viewModel()) {
        var searchText by remember { mutableStateOf(TextFieldValue()) }
        val state by viewModel.container.stateFlow.collectAsState()

        Surface(color = Color.White) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                SearchBar(searchText) { query ->
                    searchText = query
                    viewModel.initSearch(query.text)
                }

                when (state) {
                    is SearchState.Loading -> {
                        CircularProgressIndicator()
                    }

                    is SearchState.Success -> {
                        SearchResults((state as SearchState.Success).results)
                    }

                    is SearchState.Error -> {
                        val errorMessage = (state as SearchState.Error).message
                        Text(text = "Error: $errorMessage")
                    }
                }
            }
        }
    }

    @Composable
    fun SearchBar(
        searchText: TextFieldValue,
        onSearchTextChanged: (TextFieldValue) -> Unit,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .height(50.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Icon",
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp, top = 10.dp, bottom = 10.dp)
                    .size(24.dp),
            )
            BasicTextField(
                value = searchText,
                onValueChange = { onSearchTextChanged(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
            )
        }
    }

    @Composable
    fun SearchResults(results: List<Document>) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(results) { document ->
                SearchResultItem(document)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

    @Composable
    fun SearchResultItem(document: Document) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.White)
                .padding(8.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxHeight(),
            ) {
                Image(
                    painter = rememberAsyncImagePainter(document.image_url),
                    contentDescription = "thumbnail",
                    modifier = Modifier
                        .size(60.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxHeight(),
                ) {
                    Text(
                        text = document.display_sitename,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(bottom = 4.dp, end = 50.dp),
                    )
                    Text(
                        text = document.doc_url,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(bottom = 4.dp),
                    )
                    Text(
                        text = document.datetime,
                        fontSize = 10.sp,
                        color = Color.Gray,
                    )
                }
            }
            Text(
                text = document.collection,
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 8.dp, top = 8.dp),
            )
        }
    }

    @Preview
    @Composable
    fun PreviewSearchUI() {
        SearchDocument()
    }
}
