package com.example.composestudy.data.source

import com.example.composestudy.data.service.KakaoApiService

class KakaoDataSource(private val apiService: KakaoApiService) {
    suspend fun searchImages(authorization: String, query: String) =
        apiService.searchImages(authorization, query)
}
