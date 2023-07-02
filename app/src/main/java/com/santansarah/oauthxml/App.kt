package com.santansarah.oauthxml

import android.app.Application
import com.santansarah.oauthxml.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyCode: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MyCode)
            modules(appModule)
        }

    }
}