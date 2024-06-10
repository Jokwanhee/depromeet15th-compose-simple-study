package com.example.composestudy.domain

interface KakaoRepository {
    suspend fun searchImages(authorization: String, query: String): List<Document>
}
