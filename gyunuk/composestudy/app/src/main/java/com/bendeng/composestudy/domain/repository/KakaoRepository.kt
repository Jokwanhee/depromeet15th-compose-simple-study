package com.bendeng.composestudy.domain.repository

import com.bendeng.composestudy.domain.model.NIASearchPhotosData
import com.bendeng.composestudy.domain.model.base.BaseState
import kotlinx.coroutines.flow.Flow

interface KakaoRepository {

    fun getSearchPhotos(
        query: String,
        sort: String? = null,
        page: Int? = null,
        size: Int? = null,
    ): Flow<BaseState<NIASearchPhotosData>>
}