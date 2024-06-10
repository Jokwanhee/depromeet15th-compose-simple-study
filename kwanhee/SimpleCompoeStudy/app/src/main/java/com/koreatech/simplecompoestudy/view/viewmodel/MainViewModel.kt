package com.koreatech.simplecompoestudy.view.viewmodel

import androidx.lifecycle.ViewModel
import com.koreatech.simplecompoestudy.repository.SearchRepositoryImpl
import com.koreatech.simplecompoestudy.view.MainSideEffect
import com.koreatech.simplecompoestudy.view.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchRepositoryImpl: SearchRepositoryImpl,
) : ContainerHost<MainState, MainSideEffect>, ViewModel() {
    override val container: Container<MainState, MainSideEffect> = container(MainState())

    fun loadSearchImages(query: String) = intent {
        searchRepositoryImpl.searchImages(
            query = query,
            pageSize = 80
        )
    }
}