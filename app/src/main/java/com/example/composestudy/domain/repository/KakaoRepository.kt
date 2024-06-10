package com.example.composestudy.domain.repository

import com.example.composestudy.domain.entity.Document

interface KakaoRepository {
    suspend fun searchImages(authorization: String, query: String): List<Document>
}
