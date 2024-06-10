package com.koreatech.simplecompoestudy.view.viewmodel

import androidx.lifecycle.ViewModel
import com.koreatech.simplecompoestudy.repository.SearchRepositoryImpl
import com.koreatech.simplecompoestudy.view.MainSideEffect
import com.koreatech.simplecompoestudy.view.MainState
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel(
    private val searchRepositoryImpl: SearchRepositoryImpl,
) : ContainerHost<MainState, MainSideEffect>, ViewModel() {
    override val container: Container<MainState, MainSideEffect> = container(MainState())
}