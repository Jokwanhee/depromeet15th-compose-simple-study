package com.bendeng.composestudy.data.remote

import com.bendeng.composestudy.data.model.response.NIASearchPhotosResponse
import retrofit2.Response
import retrofit2.http.Query
import retrofit2.http.GET

interface KakaoApi {

    @GET("v2/search/image")
    suspend fun getSearchPhotos(
        @Query("query") query : String,
        @Query("sort") sort : String? = null,
        @Query("page")page : Int? = null,
        @Query("size")size : Int? = null
    ) : Response<NIASearchPhotosResponse>
}