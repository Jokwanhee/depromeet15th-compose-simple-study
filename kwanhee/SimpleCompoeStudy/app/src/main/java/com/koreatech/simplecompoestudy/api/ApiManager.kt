package com.koreatech.simplecompoestudy.api

import com.koreatech.simplecompoestudy.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiManager {
    private const val BASE_URL = BuildConfig.base_url
    private const val REST_API_KEY = BuildConfig.rest_api_key

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "KakaoAK $REST_API_KEY")
                .build()
            chain.proceed(newRequest)
        }
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val kakaoService = retrofit.create(ApiService::class.java)
}