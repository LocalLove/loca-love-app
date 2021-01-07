package ru.nsu.localove

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LocaLoveApplication : Application() {
    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}