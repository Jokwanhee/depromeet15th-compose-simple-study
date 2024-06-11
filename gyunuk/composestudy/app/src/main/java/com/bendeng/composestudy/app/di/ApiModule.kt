package com.bendeng.composestudy.app.di

import com.bendeng.composestudy.data.remote.KakaoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideUnplashService(retrofit: Retrofit): KakaoApi =
        retrofit.create(KakaoApi::class.java)
}