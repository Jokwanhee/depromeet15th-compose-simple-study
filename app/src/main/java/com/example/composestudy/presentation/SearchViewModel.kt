package com.example.composestudy.presentation

import androidx.lifecycle.ViewModel
import com.example.composestudy.Document
import com.example.composestudy.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

sealed class SearchState {
    object Loading : SearchState()
    data class Success(val results: List<Document>) : SearchState()
    data class Error(val message: String) : SearchState()
}

class SearchViewModel : ViewModel(), ContainerHost<SearchState, Nothing> {
    override val container = container<SearchState, Nothing>(SearchState.Loading)

    fun initSearch(query: String) = intent {
        reduce { SearchState.Loading }
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitInstance.api.searchImages("KakaoAK 66286f992570664680aa4e2ca8b2375c", query)
            }
            reduce { SearchState.Success(response.documents) }
        } catch (e: Exception) {
            reduce { SearchState.Error(e.message ?: "Unknown Error") }
        }
    }
}
