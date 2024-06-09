package com.bendeng.composestudy.data.model

import com.bendeng.composestudy.domain.model.base.BaseState
import retrofit2.Response

suspend fun <T> runRemote(block : suspend () -> Response<T>) : BaseState<T> {
    return try {
        val response = block()
        if(response.isSuccessful){
            response.body()?.let {
                BaseState.Success(it)
            } ?: BaseState.Error("응답 empty")
        } else{
            BaseState.Error("에러코드 : ${response.code()}")
        }
    } catch (e : Exception){
        BaseState.Error("에러")
    }
}