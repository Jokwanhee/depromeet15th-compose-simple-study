package com.example.composestudy.presentation

import androidx.lifecycle.ViewModel
import com.example.composestudy.domain.entity.Document
import com.example.composestudy.domain.repository.KakaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

sealed class SearchAction {
    data class Search(val query: String) : SearchAction()
}

data class SearchViewState(
    val searchState: SearchState = SearchState.Loading,
)

sealed class SearchState {
    object Loading : SearchState()
    data class Success(val results: List<Document>) : SearchState()
    data class Error(val message: String) : SearchState()
}

sealed class SearchSideEffect {
    data class ShowMessage(val message: String) : SearchSideEffect()
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: KakaoRepository,
) : ViewModel(), ContainerHost<SearchViewState, SearchSideEffect> {
    override val container = container<SearchViewState, SearchSideEffect>(SearchViewState())

    fun processAction(action: SearchAction) {
        when (action) {
            is SearchAction.Search -> {
                initSearch(action.query)
            }
        }
    }

    private fun initSearch(query: String) = intent {
        reduce { state.copy(searchState = SearchState.Loading) }
        try {
            val results = repository.searchImages("KakaoAK 66286f992570664680aa4e2ca8b2375c", query)
            reduce { state.copy(searchState = SearchState.Success(results)) }
            postSideEffect(SearchSideEffect.ShowMessage("Search completed successfully"))
        } catch (e: Exception) {
            reduce { state.copy(searchState = SearchState.Error(e.message ?: "Unknown Error")) }
            postSideEffect(SearchSideEffect.ShowMessage("Error occurred: ${e.message}"))
        }
    }
}
