package com.test.dojoincodetest

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import timber.log.Timber


class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        appContext = applicationContext
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    companion object {
        @JvmStatic
        var appContext: Context? = null
    }
}