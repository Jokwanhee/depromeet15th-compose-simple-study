package com.example.composestudy.data

import com.example.composestudy.domain.Document
import com.example.composestudy.domain.KakaoRepository

class KakaoRepositoryImpl(private val apiService: KakaoDataSource) : KakaoRepository {
    override suspend fun searchImages(authorization: String, query: String): List<Document> {
        val response = apiService.searchImages(authorization, query)
        return response.documents
    }
}
