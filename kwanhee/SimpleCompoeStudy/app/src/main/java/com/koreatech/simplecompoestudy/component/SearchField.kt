package com.koreatech.simplecompoestudy.component

import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onClear: () -> Unit,
) {
    TextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        trailingIcon = {
            IconButton(onClick = onClear) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        },
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.LightGray,
            focusedContainerColor = Color.LightGray,
            cursorColor = Color.Black,
            unfocusedTrailingIconColor = Color.Gray,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(40.dp),
        modifier = modifier
            .width(IntrinsicSize.Min)
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun SearchFieldPreview() {
    Box(modifier = Modifier.fillMaxWidth()) {
        SearchField(
            searchText = "",
            onSearchTextChange = {},
            onClear = {}
        )
    }
}