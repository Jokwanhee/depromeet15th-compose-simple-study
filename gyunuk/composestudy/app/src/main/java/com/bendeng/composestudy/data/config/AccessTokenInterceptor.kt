package com.bendeng.composestudy.data.config

import com.bendeng.composestudy.BuildConfig
import com.kakao.sdk.common.Constants.AUTHORIZATION
import com.kakao.sdk.common.Constants.KAKAO_AK
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AccessTokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val clientToken = BuildConfig.KAKAO_TOKEN
        val builder: Request.Builder = chain.request().newBuilder()
        clientToken.let {
            builder.addHeader(AUTHORIZATION, "$KAKAO_AK $it")
        }
        return chain.proceed(builder.build())
    }
}