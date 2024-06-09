package com.bendeng.composestudy.domain.model

import com.bendeng.composestudy.domain.model.base.BaseDomainModel

data class NIASearchPhotosData(
    val documents: List<DocumentData>,
) : BaseDomainModel

data class DocumentData(
    val collection: String,
    val thumbnailUrl: String,
    val imageUrl: String,
    val width: Int,
    val height: Int,
    val displaySitename: String,
    val docUrl: String,
    val datetime: String,
)