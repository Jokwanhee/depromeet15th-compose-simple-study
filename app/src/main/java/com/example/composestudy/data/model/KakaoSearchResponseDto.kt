package com.example.composestudy.data.model

import com.example.composestudy.domain.entity.Document
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KakaoSearchResponse(
    @SerialName("meta")
    val meta: Meta,
    @SerialName("documents")
    val documents: List<Document>,
)

data class Meta(
    @SerialName("total_count")
    val total_count: Int,
    @SerialName("pageable_count")
    val pageable_count: Int,
    @SerialName("is_end")
    val is_end: Boolean,
)
