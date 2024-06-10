package com.example.composestudy

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: KakaoApiService by lazy {
        retrofit.create(KakaoApiService::class.java)
    }
}
