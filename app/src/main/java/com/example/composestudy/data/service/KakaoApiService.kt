package com.example.composestudy.data.service

import com.example.composestudy.data.model.KakaoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoApiService {
    @GET("/v2/search/image")
    suspend fun searchImages(
        @Header("Authorization") authorization: String,
        @Query("query") query: String,
    ): KakaoSearchResponse
}
