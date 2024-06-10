package com.example.composestudy.data

class KakaoDataSource(private val apiService: KakaoApiService) {
    suspend fun searchImages(authorization: String, query: String) =
        apiService.searchImages(authorization, query)
}
