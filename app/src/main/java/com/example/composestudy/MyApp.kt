package com.example.composestudy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.orbitmvi.orbit.compose.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
