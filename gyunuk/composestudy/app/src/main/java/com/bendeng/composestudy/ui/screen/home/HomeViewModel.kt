package com.bendeng.composestudy.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bendeng.composestudy.domain.model.DocumentData
import com.bendeng.composestudy.domain.model.base.BaseState
import com.bendeng.composestudy.domain.repository.KakaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

data class HomeState(
    val photos: List<DocumentData> = emptyList(),
    val searchText: String = "",
    val loading: Boolean = false,
    val error: String? = null,
)

sealed class HomeSideEffect {
    data class Toast(val text: String) : HomeSideEffect()
}

/**
 * View(Model(Intent()))의 순수 함수 구조
 * 화면이동, 로깅,토스트 등 을 이용하기 위한 SideEffect
 *
 * viewmodel이 containter host가 되어 containter를 관리
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: KakaoRepository,
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {
    /**
     * viewmodel -> containerHost
     * container -> state,sideEffect 팩토리 함수를 활용
     * intent : 컨테이터 내에 있는 상태 및 부수효과
     * reduce : 현재 상태와 들어온 이벤트를 토대로 한 새로운 상태
     * postSideEffect : 상태 변경과 관련없는 이벤트 처리하기 위한 sideEffect
     */
    override val container = container<HomeState, HomeSideEffect>(HomeState())

    fun searchPhotoList(query: String) = intent {
        viewModelScope.launch {
            reduce { state.copy(loading = true) }
            try {
                repository.getSearchPhotos(query).collect { response ->
                    if (response is BaseState.Success) {
                        val photos = response.data.documents
                        reduce { state.copy(photos = photos, loading = false) }
                        postSideEffect(HomeSideEffect.Toast("이미지 불러오기가 완료"))
                    } else if (response is BaseState.Error) {
                        reduce { state.copy(loading = false) }
                        postSideEffect(HomeSideEffect.Toast(response.message))
                    }
                }
            } catch (e: Exception) {
                reduce { state.copy(loading = false) }
                postSideEffect(HomeSideEffect.Toast("이미지 불러오기 중 오류가 발생"))
            }
        }
    }
}