package com.shamilov.core.android

import android.app.Application
import com.shamilov.core.android.di.androidContext
import com.shamilov.core.di.appModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}
