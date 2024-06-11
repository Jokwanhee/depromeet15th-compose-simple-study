package com.koreatech.simplecompoestudy.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.koreatech.simplecompoestudy.repository.SearchRepositoryImpl
import com.koreatech.simplecompoestudy.view.MainSideEffect
import com.koreatech.simplecompoestudy.view.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchRepositoryImpl: SearchRepositoryImpl,
) : ContainerHost<MainState, MainSideEffect>, ViewModel() {
    override val container: Container<MainState, MainSideEffect> = container(MainState())

    val images = container.stateFlow
        .debounce(500)
        .flatMapLatest { state ->
            if (state.searchText.isEmpty()) {
                flowOf(PagingData.empty())
            } else {
                searchRepositoryImpl.searchImages(
                    query = state.searchText,
                    pageSize = 20
                ).cachedIn(viewModelScope)
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            PagingData.empty()
        )

    fun updateSearchText(text: String) = intent {
        reduce { state.copy(searchText = text) }
    }

    fun clearSearchText() = intent {
        reduce { state.copy(searchText = "") }
    }
}

