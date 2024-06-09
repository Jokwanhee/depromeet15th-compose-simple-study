package com.bendeng.composestudy.data.repository

import com.bendeng.composestudy.data.model.response.NIASearchPhotosResponse.Companion.toDomainModel
import com.bendeng.composestudy.data.model.runRemote
import com.bendeng.composestudy.data.remote.KakaoApi
import com.bendeng.composestudy.domain.model.NIASearchPhotosData
import com.bendeng.composestudy.domain.model.base.BaseState
import com.bendeng.composestudy.domain.repository.KakaoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class KakaoRepositoryImpl @Inject constructor(private val api: KakaoApi) : KakaoRepository {
    override fun getSearchPhotos(
        query: String,
        sort: String?,
        page: Int?,
        size: Int?,
    ): Flow<BaseState<NIASearchPhotosData>> = flow{
        when(val result = runRemote { api.getSearchPhotos(query, sort, page, size)}){
            is BaseState.Success -> {
                emit(BaseState.Success(result.data.toDomainModel()))
            }
            is BaseState.Error ->{
                emit(result)
            }
        }
    }
}