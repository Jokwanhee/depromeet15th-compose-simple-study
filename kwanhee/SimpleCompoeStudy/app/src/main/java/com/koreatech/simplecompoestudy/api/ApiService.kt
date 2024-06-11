package com.koreatech.simplecompoestudy.api

import com.koreatech.simplecompoestudy.BuildConfig
import com.koreatech.simplecompoestudy.data.response.SearchImageResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization: KakaoAK ${BuildConfig.rest_api_key}")
    @GET("v2/search/image")
    suspend fun getSearchImage(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): SearchImageResponse
}