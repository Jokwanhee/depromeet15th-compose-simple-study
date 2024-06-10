package com.koreatech

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.koreatech.simplecompoestudy.BuildConfig

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.kakao_native_app_key)
    }
}