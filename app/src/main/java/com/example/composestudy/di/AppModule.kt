package com.example.composestudy.di

import com.example.composestudy.data.KakaoApiService
import com.example.composestudy.data.KakaoDataSource
import com.example.composestudy.data.KakaoRepositoryImpl
import com.example.composestudy.domain.KakaoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideKakaoApiService(): KakaoApiService {
        return Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KakaoApiService::class.java)
    }

    @Provides
    fun provideKakaoDataSource(apiService: KakaoApiService): KakaoDataSource {
        return KakaoDataSource(apiService)
    }

    @Provides
    fun provideKakaoRepository(dataSource: KakaoDataSource): KakaoRepository {
        return KakaoRepositoryImpl(dataSource)
    }
}
