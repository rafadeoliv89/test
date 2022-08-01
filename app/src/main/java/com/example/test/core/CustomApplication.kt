package com.example.test.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CustomApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}