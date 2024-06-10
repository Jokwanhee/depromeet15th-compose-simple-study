package com.example.composestudy.data
import com.example.composestudy.domain.Document

data class KakaoSearchResponse(
    val meta: Meta,
    val documents: List<Document>,
)

data class Meta(
    val total_count: Int,
    val pageable_count: Int,
    val is_end: Boolean,
)
