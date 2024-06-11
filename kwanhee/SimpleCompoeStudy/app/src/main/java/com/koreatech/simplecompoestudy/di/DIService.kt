package com.koreatech.simplecompoestudy.di

import com.koreatech.simplecompoestudy.data.datasource.SearchDataSourceImpl
import com.koreatech.simplecompoestudy.repository.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DIService {
    @Singleton
    @Provides
    fun providesSearchDataSourceImpl(): SearchDataSourceImpl = SearchDataSourceImpl()

    @Singleton
    @Provides
    fun providesSearchRepositoryImpl(
        searchDataSourceImpl: SearchDataSourceImpl,
    ): SearchRepositoryImpl = SearchRepositoryImpl(searchDataSourceImpl)
}