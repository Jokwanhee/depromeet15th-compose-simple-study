package com.koreatech.simplecompoestudy.view

sealed class MainSideEffect {
    data class Toast(val message: String) : MainSideEffect()
}