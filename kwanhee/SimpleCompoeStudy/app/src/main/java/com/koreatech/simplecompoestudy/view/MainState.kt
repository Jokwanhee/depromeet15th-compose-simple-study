package com.koreatech.simplecompoestudy.view

import com.koreatech.simplecompoestudy.data.response.SearchImageDocumentsResponse

data class MainState(
    val images: List<SearchImageDocumentsResponse> = emptyList(),
    val searchText: String = ""
)