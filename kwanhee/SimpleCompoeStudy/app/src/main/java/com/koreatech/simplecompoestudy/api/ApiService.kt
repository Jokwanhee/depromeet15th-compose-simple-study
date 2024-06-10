package com.koreatech.simplecompoestudy.api

import com.koreatech.simplecompoestudy.data.response.SearchImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/search/image")
    suspend fun getSearchImage(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): SearchImageResponse
}