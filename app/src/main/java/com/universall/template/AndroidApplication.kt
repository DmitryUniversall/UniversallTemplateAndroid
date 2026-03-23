package com.universall.template

import android.app.Application
import com.google.crypto.tink.config.TinkConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
internal class AndroidApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initTink()
    }

    private fun initTink() {
        TinkConfig.register()
    }
}