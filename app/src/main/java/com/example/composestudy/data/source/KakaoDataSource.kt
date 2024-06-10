package com.example.composestudy.data.source

import com.example.composestudy.data.service.KakaoApiService
import javax.inject.Inject

class KakaoDataSource @Inject constructor(
    private val apiService: KakaoApiService,
) {
    suspend fun searchImages(authorization: String, query: String) =
        apiService.searchImages(authorization, query)
}
