package com.bendeng.composestudy.data.model.response

import com.bendeng.composestudy.data.model.base.BaseDataModel
import com.bendeng.composestudy.data.model.mapper.DomainMapper
import com.bendeng.composestudy.domain.model.DocumentData
import com.bendeng.composestudy.domain.model.NIASearchPhotosData
import com.google.gson.annotations.SerializedName

data class NIASearchPhotosResponse(
    val documents: List<Document>,
    val meta: Meta,
) : BaseDataModel {
    companion object : DomainMapper<NIASearchPhotosResponse, NIASearchPhotosData> {
        override fun NIASearchPhotosResponse.toDomainModel(): NIASearchPhotosData =
            NIASearchPhotosData(
                documents = documents.map { document ->
                    DocumentData(
                        collection = document.collection.ifEmpty { "None" },
                        thumbnailUrl = document.thumbnailUrl,
                        imageUrl = document.imageUrl,
                        displaySitename = document.displaySitename.ifEmpty { "사이트 명 모름" },
                        docUrl = document.docUrl.ifEmpty { "사이트 주소 알 수 없음" },
                        datetime = if (document.datetime.isEmpty()) "날짜 알 수 없음" else document.datetime
                    )
                }
            )
    }
}


data class Meta(
    @SerializedName("is_end") val isEnd: Boolean,
    @SerializedName("pageable_count") val pageableCount: Int,
    @SerializedName("total_count") val totalCount: Int,
)

data class Document(
    @SerializedName("collection") val collection: String,
    @SerializedName("datetime") val datetime: String,
    @SerializedName("display_sitename") val displaySitename: String,
    @SerializedName("doc_url") val docUrl: String,
    @SerializedName("height") val height: Int,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String,
    @SerializedName("width") val width: Int,
)