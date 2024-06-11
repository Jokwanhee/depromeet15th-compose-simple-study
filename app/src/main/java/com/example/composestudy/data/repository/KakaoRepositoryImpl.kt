package com.example.composestudy.data.repository

import com.example.composestudy.data.source.KakaoDataSource
import com.example.composestudy.domain.entity.Document
import com.example.composestudy.domain.repository.KakaoRepository
import javax.inject.Inject

class KakaoRepositoryImpl @Inject constructor(private val apiService: KakaoDataSource) : KakaoRepository {
    override suspend fun searchImages(authorization: String, query: String): List<Document> {
        val response = apiService.searchImages(authorization, query)
        return response.documents
    }
}
