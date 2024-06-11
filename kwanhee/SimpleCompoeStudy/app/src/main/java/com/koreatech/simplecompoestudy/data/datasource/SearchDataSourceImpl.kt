package com.koreatech.simplecompoestudy.data.datasource

import com.koreatech.simplecompoestudy.api.ApiManager
import com.koreatech.simplecompoestudy.data.response.SearchImageResponse

class SearchDataSourceImpl() {
    suspend fun getSearchImage(
        query: String,
        sort: String = "accuracy",
        page: Int,
        size: Int,
    ): SearchImageResponse {
        return ApiManager.kakaoService.getSearchImage(
            query = query,
            sort = sort,
            page = page,
            size = size
        )
    }
}