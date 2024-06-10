package com.koreatech.simplecompoestudy.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.koreatech.simplecompoestudy.data.datasource.SearchDataSourceImpl
import com.koreatech.simplecompoestudy.data.response.SearchImageDocumentsResponse
import com.koreatech.simplecompoestudy.data.response.SearchImageResponse
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
    private val dataSourceImpl: SearchDataSourceImpl,
) {
    fun searchImages(query: String, pageSize: Int): Flow<PagingData<SearchImageDocumentsResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchPagingSource(query, dataSourceImpl) }
        ).flow
    }
}

class SearchPagingSource(
    private val query: String,
    private val dataSourceImpl: SearchDataSourceImpl,
) : PagingSource<Int, SearchImageDocumentsResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchImageDocumentsResponse> {
        val page = params.key ?: 1
        val data: SearchImageResponse
        try {
            data = dataSourceImpl.getSearchImage(query = query, page = page, size = params.loadSize)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
        return LoadResult.Page(
            data = data.documents,
            prevKey = if (page == 0) null else page - 1,
            nextKey = if (data.documents.isEmpty()) null else page + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, SearchImageDocumentsResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}