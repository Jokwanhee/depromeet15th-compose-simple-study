package com.example.composestudy

data class KakaoSearchResponse(
    val meta: Meta,
    val documents: List<Document>,
)

data class Meta(
    val total_count: Int,
    val pageable_count: Int,
    val is_end: Boolean,
)

data class Document(
    val collection: String,
    val thumbnail_url: String,
    val image_url: String,
    val display_sitename: String,
    val doc_url: String,
    val datetime: String,
)
