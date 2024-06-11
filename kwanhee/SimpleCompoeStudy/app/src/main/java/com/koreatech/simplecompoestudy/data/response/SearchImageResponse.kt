package com.koreatech.simplecompoestudy.data.response

import com.google.gson.annotations.SerializedName

data class SearchImageResponse(
    @SerializedName("meta")
    val meta: SearchImageMetaResponse,
    @SerializedName("documents")
    val documents: List<SearchImageDocumentsResponse>,
)

data class SearchImageMetaResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("pageable_count")
    val pageableCount: Int,
    @SerializedName("is_end")
    val isEnd: Boolean,
)

data class SearchImageDocumentsResponse(
    @SerializedName("collection")
    val collection: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("display_sitename")
    val displaySitename: String,
    @SerializedName("doc_url")
    val docUrl: String,
    @SerializedName("datetime")
    val datetime: String,
)