package com.example.composestudy.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composestudy.domain.entity.Document
import com.example.composestudy.domain.repository.KakaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SearchState {
    object Loading : SearchState()
    data class Success(val results: List<Document>) : SearchState()
    data class Error(val message: String) : SearchState()
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: KakaoRepository,
) : ViewModel() {

    private val _searchState = MutableStateFlow<SearchState>(SearchState.Loading)
    val searchState: StateFlow<SearchState> = _searchState.asStateFlow()

    fun initSearch(query: String) {
        viewModelScope.launch {
            _searchState.value = SearchState.Loading
            try {
                val results = repository.searchImages("KakaoAK 66286f992570664680aa4e2ca8b2375c", query)
                _searchState.value = SearchState.Success(results)
            } catch (e: Exception) {
                _searchState.value = SearchState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}
