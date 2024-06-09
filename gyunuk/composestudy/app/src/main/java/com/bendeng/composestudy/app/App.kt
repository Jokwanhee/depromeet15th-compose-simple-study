package com.bendeng.composestudy.app

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App  : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}